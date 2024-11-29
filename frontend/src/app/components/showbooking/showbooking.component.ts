import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookingService } from '../../services/bookingService/booking.service';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PassengerService } from '../../services/passengerService/passenger.service';
import { Passenger } from '../../model/passenger.model';
import { AuthService } from '../../services/authService/auth.service';
import { DiscountService } from '../../services/discountService/discount.service';
import { User } from '../../model/user.model';
import { UserService } from '../../services/userService/user.service';

@Component({
  selector: 'app-showbooking',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './showbooking.component.html',
  styleUrl: './showbooking.component.css'
})
export class ShowbookingComponent implements OnInit{
  
  bookingId!: number;
  bookingDetails: any;
  passengerForm!: FormGroup;
  noOfNights: number = 0;

  error: string = '';
  loading: boolean = true;

  loggedInUser: any;
  userId: string | null = null;
  userDetails: User | null = null;

  public grandTotal: number = 0;
  public discountAmount: number = 0;
  public netAmount: number = 0;
  public balancedAmount: number = 0;
  public prepaymentAmount: number = 0;
  

  constructor(
    private router: Router, 
    private route: ActivatedRoute, 
    private bookingSerive: BookingService, 
    private passengerService: PassengerService,
    private authService: AuthService,
    private discountService: DiscountService,
    private userService: UserService
  ) {}

  ngOnInit(): void {

    this.userId= this.authService.getUserIdFromToken();
    console.log('User ID from token:', this.userId);
    if (this.userId) {
      this.fetchUser(this.userId);
      // console.log('Pamali', this.userDetails?.userId);
    }

    this.bookingId = Number(+this.route.snapshot.paramMap.get('bookingId')!);
    console.log('booking id found!', this.bookingId);
    this.getBooking();

    this.passengerForm = new FormGroup({
      passengerName: new FormControl('', [Validators.required]),
      passengerEmail: new FormControl('', [Validators.required, Validators.email]),
      passengerNic: new FormControl('', [Validators.required])
    });
  }

  onSubmit(): void{
    if(this.passengerForm.valid){
      const newPassenger: Passenger = this.passengerForm.value;
      this.passengerService.addPassenger(newPassenger).subscribe({
        next: (response) => {
          console.log('New Passenger added!', response);
          this.bookingSerive.assignPassengerToBooking(this.bookingId, response.passengerId).subscribe({
            next: (data) => {
              console.log('Passenger added to booking', this.bookingId);
              alert('Passenger added to the current booking');
            },
            error: (e) => {
              console.error('Passenger didnt added to the booking id', e);
            }
          })

        },
        error: (e) => {
          console.error('Error adding passengers', e);
        }
      })
    }
  }

  getBooking(): void{
    this.bookingSerive.getBookingById(this.bookingId).subscribe({
      next: (response) => {
        this.bookingDetails = response;
        this.loading = false;
        this.calculateGrandTotal();
        console.log("1 called");
        // if (this.bookingDetails.discount.discountId) {
        //   console.log("2 called");
        //   this.getDiscountAndApply();
        // }
        if (this.bookingDetails.discount && this.bookingDetails.discount.discountId) {
          console.log("Discount exists, fetching discount details.");
          this.getDiscountAndApply();
        } else {
          console.log("No discount applied.");
          this.calculateTotalWithoutDiscount();
        }

      },
      error: (e) => {
        console.log("failed to load booking", this.bookingId);
        this.error = 'Failed to load booking details';
        this.loading = false;
      }
    })
  }

  calculateBalancedAmount(): number {
    if (this.balancedAmount < 0) {
      this.balancedAmount = 0;
    }

    this.balancedAmount = this.netAmount - this.prepaymentAmount;
  
    return this.balancedAmount;
  }

  calculateTotalWithoutDiscount(): number{

    this.discountAmount = 0;

    this.netAmount = this.grandTotal - this.discountAmount

    this.calculateBalancedAmount();
    console.log('Balanced AMount', this.balancedAmount);

    return this.grandTotal;
  }
  

  calculateGrandTotal(): void {
    let roomsTotal = 0;
    let supplementTotal = 0;

    if (this.bookingDetails.bookingRoomtypes && this.bookingDetails.bookingRoomtypes.length > 0) {
      roomsTotal = this.bookingDetails.bookingRoomtypes.reduce((acc: number, room: any) => acc + room.totalPrice, 0);
    }

    if (this.bookingDetails.bookingSupplements && this.bookingDetails.bookingSupplements.length > 0) {
      supplementTotal = this.bookingDetails.bookingSupplements.reduce((acc: number, supplement: any) => acc + supplement.totalPrice, 0);
    }

    this.grandTotal = roomsTotal + supplementTotal;
    console.log('Grand Total:', this.grandTotal);
  }

  calculateDiscount(grandTotal: number, discountPercentage: number): number {
    // Convert discountPercentage from percentage (e.g. 15%) to decimal (e.g. 0.15)
    const discountFactor = discountPercentage / 100;
    
    // Calculate discount
    const discountAmount = grandTotal * discountFactor;
    
    return discountAmount;
  }
  

  getDiscountAndApply(): void {
    console.log("Fetching discount details...");
    this.discountService.getDiscountById(this.bookingDetails.discount.discountId).subscribe({
      next: (response) => {
        console.log('Discount received:', response);
      
        const discountPercentage = parseFloat(response.discountPercentage.replace('%', ''));
        
        this.discountAmount = this.calculateDiscount(this.grandTotal, discountPercentage);

        this.netAmount = this.grandTotal - this.discountAmount;
  
        console.log('Discount applied:', this.discountAmount);
        console.log('Net amount after discount:', this.netAmount);

        this.calculateBalancedAmount();
        console.log('Balanced AMount', this.balancedAmount);
      },
      error: (e) => {
        console.error('Failed to fetch discount', e);
        // Handle error as needed
      }
    });
  }
  
  
  
  calculateNoofNights(): number{

    const cin = new Date(this.bookingDetails.checkinDate);
    const cout = new Date(this.bookingDetails.checkoutDate);

    const differanceInMs = cout.getTime() - cin.getTime();
    console.log("DIfferance in Ms: ", differanceInMs);

    this.noOfNights = differanceInMs / (1000 * 60 * 60 * 24);

    return this.noOfNights;
  }

  // fetchUser(userId: string): void {
  //   this.userService.getUserByUsername(userId).subscribe(
  //     (user: User) => {
  //       this.userDetails = user;  // Store the user details in the component
  //       console.log('Fetched user details:', user);
  //     },
  //     (error) => {
  //       console.error('Error fetching user details:', error);
  //     }
  //   );
  // }

  fetchUser(userId: string): void {
    this.userService.getUserByUsername(userId).subscribe(
      (user: User) => {
        this.userDetails = user;  // Store the user details in the component
        console.log('Fetched user details:', user);
        
        // Check if userId exists in the fetched user details
        if (this.userDetails && this.userDetails.userId) {
          console.log('Pamali Fetched User ID:', this.userDetails.userId);
        } else {
          console.log('User ID not found in fetched user details');
        }
      },
      (error) => {
        console.error('Error fetching user details:', error);
      }
    );
  }
  


  onConfirmBooking(): void {
    // Ensure we have all the required fields from the existing booking details
    const updatedBooking = {
      bookingId: this.bookingId,  
      contractId: this.bookingDetails.contractId || null, 
      discountId: this.bookingDetails.discount?.discountId || null, 
      userId: this.loggedInUser?.userId || null,  
      passengerId: this.bookingDetails.passenger?.passengerId,  
      checkinDate: this.bookingDetails.checkinDate, 
      checkoutDate: this.bookingDetails.checkoutDate,
      bookingDate: this.bookingDetails.bookingDate,
      adultCount: this.bookingDetails.adultCount, 
      childCount: this.bookingDetails.childCount, 
      totalAmount: this.grandTotal, 
      discountAmount: this.discountAmount,
      netAmount: this.netAmount, 
      prepaymentAmount: this.prepaymentAmount, 
      balancedAmount: this.balancedAmount, 
      status: 'Confirmed',  
    };
  
    console.log('Booking updated', updatedBooking);
  
    
    this.bookingSerive.updateBooking(this.bookingId, updatedBooking).subscribe({
      next: (response) => {
        console.log('Booking updated successfully:', response);
        // this.router.navigateByUrl('confirmBooking');

        if (this.userDetails?.userId) {
          this.bookingSerive.assignUserToBooking(this.bookingId, this.userDetails.userId).subscribe({
            next: (assignResponse) => {
              console.log('User assigned to booking successfully:', assignResponse);
              this.router.navigateByUrl('confirmBooking');
            },
            error: (e) => {
              console.error('Error assigning user to booking:', e);
            }
          });
        }
      },
      error: (e) => {
        console.error('Error updating booking:', e);
        
      }
    });


  }
  
  


}
