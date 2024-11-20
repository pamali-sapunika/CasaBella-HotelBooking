import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookingService } from '../../services/bookingService/booking.service';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PassengerService } from '../../services/passengerService/passenger.service';
import { Passenger } from '../../model/passenger.model';
import { AuthService } from '../../services/authService/auth.service';

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
  

  constructor(
    private router: Router, 
    private route: ActivatedRoute, 
    private bookingSerive: BookingService, 
    private passengerService: PassengerService,
    private authService: AuthService 
  ) {}

  ngOnInit(): void {

    this.loggedInUser = this.authService.getUser();
    console.log('User:', this.loggedInUser);

    // if (!this.loggedInUser) {
    //   this.router.navigate(['/login']); // Redirect if the user is not logged in
    // }

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

  onConfirmBooking(): void {
    if (!this.loggedInUser) {
      // If the user is not logged in, redirect to login with the current URL as a query parameter
      this.router.navigate(['/login'], { queryParams: { returnUrl: this.router.url } });
    } else {
      // If the user is logged in, proceed with booking confirmation logic
      console.log('Booking confirmed!');
      // Add your booking confirmation logic here
    }
  }

  getBooking(): void{
    this.bookingSerive.getBookingById(this.bookingId).subscribe({
      next: (response) => {
        this.bookingDetails = response;
        this.loading = false;
      },
      error: (e) => {
        console.log("failed to load booking", this.bookingId);
        this.error = 'Failed to load booking details';
        this.loading = false;
      }
    })
  }

  
  calculateNoofNights(): number{

    const cin = new Date(this.bookingDetails.checkinDate);
    const cout = new Date(this.bookingDetails.checkoutDate);

    const differanceInMs = cout.getTime() - cin.getTime();
    console.log("DIfferance in Ms: ", differanceInMs);

    this.noOfNights = differanceInMs / (1000 * 60 * 60 * 24);

    return this.noOfNights;
  }


}
