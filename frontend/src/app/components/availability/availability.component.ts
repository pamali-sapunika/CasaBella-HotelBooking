import { Component, OnInit } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { ActivatedRoute, Router } from '@angular/router';
import { HotelsService } from '../../services/hotelService/hotels.service';
import { SeasonalRoomsService } from '../../services/seasonalRoomsService/seasonal-rooms.service';
import { AvailabilityDTO } from '../../model/availability.dto';
import { CommonModule } from '@angular/common';
import { SeasonalSupplementService } from '../../services/seasonalSupplementService/seasonal-supplement.service';
import { SearchagainComponent } from '../searchagain/searchagain.component';
import { AvaialbilitySupplements } from '../../model/availabilitySupple.dto';
import { BookingroomsService } from '../../services/bookingRoomtypeService/bookingrooms.service';
import { BookingsupplementsService } from '../../services/bookingSupplementService/bookingsupplements.service';
import { BookingService } from '../../services/bookingService/booking.service';
import { Booking } from '../../model/booking.model';
import { BookingRoomtypes } from '../../model/bookingrooms.model';
import { BookingSupplements } from '../../model/bookingsupplement.model';
import { FormsModule } from '@angular/forms';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { DiscountService } from '../../services/discountService/discount.service';
import { Discount } from '../../model/discount.model';

@Component({
  selector: 'app-availability',
  standalone: true,
  imports: [
    MatCardModule,
    MatButtonModule,
    CommonModule,
    SearchagainComponent,
    FormsModule,
    MatCheckboxModule
  ],
  templateUrl: './availability.component.html',
  styleUrls: ['./availability.component.css']
})
export class AvailabilityComponent implements OnInit {

  availabilityList: AvailabilityDTO[] = [];
  supplementCache: { [seasonalRoomtypeId: number]: AvaialbilitySupplements[] } = {};
  seasonalSupplements: AvaialbilitySupplements[] = [];
  hotelDiscounts: Discount[] = []; 

  hotelId!: number; 
  guestCount!: number; 
  checkinDate!: string;
  checkoutDate!: string;

  existingBookingId: number | null = null;
  noOfNights: number = 0;
  guestCounts: number[] = [1, 2, 3, 4, 5];
  eligibleForEarlyBird: boolean = false; 
  eligibleForDiscount: boolean = false;

  constructor(
    private router: Router, 
    private route: ActivatedRoute, 
    private hotelService: HotelsService, 
    private seasonalRoomsService: SeasonalRoomsService,
    private seasonalSupplementService: SeasonalSupplementService,
    private bookingRoomsService: BookingroomsService,
    private bookingSupplementService: BookingsupplementsService,
    private bookingService: BookingService,
    private discountService: DiscountService
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.hotelId = Number(params.get('hotelId'));
    });
    this.route.queryParamMap.subscribe(params => {
      this.guestCount = Number(params.get('guestCount'));
      this.checkinDate = this.formatDate(params.get('checkinDate')!);
      this.checkoutDate = this.formatDate(params.get('checkoutDate')!);
    });
    // this.guestCounts.forEach((guestCount) => {
    //   const totalPrice = this.calculateRoomPrice(this.unitPrice, this.maxAdults, guestCount, this.noOfNights);
    //   console.log(`Guest Count: ${guestCount}, Total Price for ${this.noOfNights} nights: $${totalPrice}`);
    // });
    this.loadAvailability();
    this.fetchDiscounts();
  }


  loadAvailability(): void {
    if (this.hotelId && this.guestCount && this.checkinDate && this.checkoutDate !== undefined) {
      this.seasonalRoomsService.getAvailability(this.hotelId, this.guestCount, this.checkinDate, this.checkoutDate).subscribe({
        next: (data) => {
          this.availabilityList = data;
  
          // Initialize selectedGuestCount with the guestCount value
          this.availabilityList.forEach((availability) => {
            availability.selectedGuestCount = this.guestCount;
            const totalPrice = this.calculateRoomPrice(
              availability.price,      
              availability.noofRooms,         
              this.noOfNights,          
              availability.markupPercentage   
            );
            console.log(`Roomtype: ${availability.roomtypeName}, Total Price for ${this.noOfNights} nights: $${totalPrice}`);
          });

          // const totalSupplementsCost = this.calculateSupplementPrice();
          // console.log(`Total Supplement Price: $${totalSupplementsCost}`);
  
          this.fetchSupplements();
          this.calculateNoofNights();
          console.log("Discounts fetching");
          
        },
        error: (e) => {
          console.error('Error fetching availability', e);
        }
      });
    }
  }

  fetchDiscounts(): void {
    this.discountService.getDiscountsByHotelId(this.hotelId).subscribe({
      next: (response) => {
        this.hotelDiscounts = response;
        console.log("Discounts fetched:", this.hotelDiscounts);

        const differenceInDays = this.calculateDateDifference(new Date(), new Date(this.checkinDate));
        console.log('differeance in fetching discount method:', differenceInDays)
        this.eligibleForDiscount = this.hotelDiscounts.some(
          (discount) => differenceInDays >= discount.daysPriorToArrival
        );

        if (this.eligibleForDiscount) {
          console.log("User is eligible for a discount.");
        } else {
          console.log("User is not eligible for any discount.");
        }
      },
      error: (e) => {
        console.error("Error Fetching Discounts", e);
      }
    });
  }
  

  calculateNoofNights(): number{

    const cin = new Date(this.checkinDate);
    const cout = new Date(this.checkoutDate);

    const differanceInMs = cout.getTime() - cin.getTime();
    console.log("DIfferance in Ms: ", differanceInMs);

    this.noOfNights = differanceInMs / (1000 * 60 * 60 * 24);

    return this.noOfNights;
  }

  // calculateRoomPrice(unitPrice: number, maxAdults: number, guestCount: number, noOfNights: number): number {
  //   const requiredRooms = Math.ceil(guestCount / maxAdults);

  //   const totalRoomPrice = requiredRooms * unitPrice * noOfNights;

  //   return totalRoomPrice;
  // }

  // calculateRoomPrice(unitPrice: number, noOfRooms: number, noOfNights: number, markupPercentage: number): number {
  //   const basePrice = unitPrice * noOfRooms * noOfNights;

  //   const totalPrice = basePrice * (1 + markupPercentage / 100);
  
  //   return totalPrice;
  // }

  calculateRoomPrice(unitPrice: number, noOfRooms: number, noOfNights: number, markupPercentage: number): number {
    const basePrice = unitPrice * noOfRooms * noOfNights;
    const totalPrice = basePrice * (1 + markupPercentage / 100);
    return totalPrice;
  }

  calculateRoomPriceFirst(unitPrice: number, noOfRooms: number, noOfNights: number): number {
    let firstbasePrice = unitPrice * noOfRooms * noOfNights;
    return firstbasePrice;
  }

  // calculateTotalPrice(availability: AvailabilityDTO): number {

  //   const roomPrice = this.calculateRoomPrice(
  //     availability.price,
  //     availability.selectedRooms || 0,
  //     this.noOfNights,
  //     availability.markupPercentage
  //   );
  //   const supplementPrice = this.calculateSupplementPrice(availability.seasonalRoomtypeId);
  //   const totalPrice = roomPrice + supplementPrice;
  //   // console.log(`Room Price: $${roomPrice}, Supplement Price: $${supplementPrice}, Total Price: $${totalPrice}`);
  
  //   return totalPrice;
  // }

  calculateTotalPrice(availability: AvailabilityDTO): number {

    const roomPrice = this.calculateRoomPrice(
      availability.price,
      availability.selectedRooms || 0,
      this.noOfNights,
      availability.markupPercentage
    );
    const supplementPrice = this.calculateSupplementPrice(availability.seasonalRoomtypeId);
    const totalPrice = roomPrice + supplementPrice;
  
    // Return both total price and the original total price (before discount)
    return totalPrice;
  }

  calculateSupplementPrice(seasonalRoomtypeId: number): number {
    const supplements = this.supplementCache[seasonalRoomtypeId];
    if (!supplements) return 0;
  
    return supplements.reduce((total, supplement) => {
      // Multiply price per unit by the selected quantity and sum the results
      const supplementTotal = supplement.selectedQuantity ? supplement.selectedQuantity * supplement.pricePerUnit : 0;
      return total + supplementTotal;
    }, 0);
  }
  


  // getTotalPrice(availability: AvailabilityDTO, supplementCache: any, noOfNights: number): number {
  //   const roomPrice = this.calculateRoomPrice(availability.price, availability.selectedRooms || 0, noOfNights, availability.markupPercentage);
    
  //   const supplementsPrice = supplementCache[availability.seasonalRoomtypeId]?.reduce(
  //     (sum: number, supplement: AvailabilitySupplements): number => {
  //       return sum + (supplement.selectedQuantity || 0) * supplement.pricePerUnit;
  //     },
  //     0
  //   ) || 0;
    
  //   return roomPrice + supplementsPrice;
  // }
  
  // private calculateDateDifference(date1: Date, date2: Date): number {
  //   const timeDifference = date2.getTime() - date1.getTime();
  //   return timeDifference / (1000 * 3600 * 24); 
  // }
  
  // // Check if discount is eligible
  // private checkEarlyBirdDiscount(differenceInDays: number): boolean {
  //   for (const discount of this.hotelDiscounts) {
  //     if (differenceInDays >= discount.daysPriorToArrival) {
  //       return true;  
  //     }
  //   }
  //   return false;  
  // }

  private calculateDateDifference(date1: Date, date2: Date): number {
    const timeDifference = date2.getTime() - date1.getTime();
    return timeDifference / (1000 * 3600 * 24); // Convert milliseconds to days
  }

  // For checking early bird discount eligibility
  private checkEarlyBirdDiscount(differenceInDays: number): Discount | null {
    return this.hotelDiscounts.find(discount => differenceInDays >= discount.daysPriorToArrival) || null;
  }


  fetchSupplements(): void {
    this.availabilityList.forEach(availability => {
      const seasonalRoomtypeId = availability.seasonalRoomtypeId;
      if (!this.supplementCache[seasonalRoomtypeId]) {
        this.seasonalSupplementService.getSeasonalSupplementsWithNames(availability.seasonId).subscribe({
          next: (data) => {
            // Clone the supplements for this specific room type
            this.supplementCache[seasonalRoomtypeId] = data.map(supplement => ({
              ...supplement,
              selectedQuantity: 0 // Initialize selectedQuantity
            }));
          },
          error: (e) => {
            console.error(`Error fetching supplements for seasonalRoomtypeId ${seasonalRoomtypeId}:`, e);
          }
        });
      }
    });
  }
  

  onSubmit(): void {
    if (this.existingBookingId) {
      console.log(`Using existing booking with ID: ${this.existingBookingId}`);
      this.addMultipleBookingRoomtypes(this.existingBookingId);
      this.addSupplementsToBooking(this.existingBookingId);
    } else {
      const newBooking: Booking = {
        bookingId: 0,
        contractId: 0,
        discountId: 0,
        userId: 0,
        passengerId: 0,
        checkinDate: this.checkinDate,
        checkoutDate: this.checkoutDate,
        bookingDate: new Date().toISOString(),
        adultCount: this.guestCount,
        childCount: 0, 
        totalAmount: 0,
        discountAmount: 0,
        netAmount: 0,
        prepaymentAmount: 0,
        balancedAmount: 0,
        status: 'UnConfirmed'
      };
  
      this.bookingService.addBooking(newBooking).subscribe({
        next: (booking) => {
          this.existingBookingId = booking.bookingId;
          console.log(`New booking created: ${booking.bookingId}`, booking);

          // const contractId = this.getContractId();
          // this.bookingService.assignContractToBooking(booking.bookingId, contractId).subscribe({
          //   next: (updatedBooking) => {
          //     console.log(`Contract assigned to booking ${updatedBooking.bookingId}`, updatedBooking);
          //     this.addMultipleBookingRoomtypes(updatedBooking.bookingId);
          //     this.addSupplementsToBooking(updatedBooking.bookingId);
          //   },
          //   error: (e) => {
          //     console.error(`Error assigning contract to booking ${booking.bookingId}:`, e);
          //   }
          // });

          const bookingDate = new Date(booking.bookingDate);
          console.log('booking date', bookingDate);
          const currentDate = new Date();
          console.log('current date', currentDate);
          const checkinDate = new Date(this.checkinDate);
          const differenceInDays = this.calculateDateDifference(bookingDate, checkinDate);

          // Check if early bird discount applies
          const discount = this.checkEarlyBirdDiscount(differenceInDays);

          if (discount) {
            this.bookingService.assignDiscountToBooking(booking.bookingId, discount.discountId).subscribe({
              next: (updatedBooking) => {
                console.log(`Discount applied to booking ${updatedBooking.bookingId}`, updatedBooking);
              },
              error: (e) => {
                console.error('Error applying discount to booking:', e);
              }
            });
          }else{
            console.log('No discounts to add');
          }

          console.log(`Early Bird Discount Eligibility: ${discount}`);

          this.addMultipleBookingRoomtypes(booking.bookingId);
          this.addSupplementsToBooking(booking.bookingId); 
        },
        error: (e) => {
          console.error('Error creating booking:', e);
        }
      });
    }
  }

  // getContractId(): number{
  //   return 1;
  // }

  addSupplementsToBooking(bookingId: number): void {
    for (const availability of this.availabilityList) {
      const seasonalRoomtypeId = availability.seasonalRoomtypeId;
      const supplements = this.supplementCache[seasonalRoomtypeId];
  
      if (supplements) {
        supplements.forEach(supplement => {
          if (supplement.selectedQuantity && supplement.selectedQuantity > 0) {

            const totalPrice = supplement.selectedQuantity * supplement.pricePerUnit;

            const bookingSupplement: BookingSupplements = {
              bookingSupplementId: 0,
              bookingId: bookingId,
              seasonalSupplementId: supplement.seasonalSupplementId,
              quantity: supplement.selectedQuantity,
              pricePerUnit: supplement.pricePerUnit,
              totalPrice: totalPrice
            };
  
            this.bookingSupplementService.addSupplementToBooking(bookingId, supplement.seasonalSupplementId, bookingSupplement).subscribe({
              next: (response) => {
                console.log(`Supplement added to booking ${bookingId}:`, response);
              },
              error: (e) => {
                console.error(`Error adding supplement to booking ${bookingId}:`, e);
              }
            });
          }
        });
      }
    }
  }
  

  addMultipleBookingRoomtypes(bookingId: number): void {
    for (const availability of this.availabilityList) {
      if (availability.selectedRooms && availability.selectedRooms > 0) {

        const totalPrice = this.calculateRoomPrice(
          availability.price,
          availability.selectedRooms,
          this.noOfNights,
          availability.markupPercentage
        );
        
        const bookingRoomtype: BookingRoomtypes = {
          bookingRoomtypeId: 0,
          bookingId: bookingId,
          seasonalRoomtypeId: availability.seasonalRoomtypeId,
          noOfRooms: availability.selectedRooms,
          price: availability.price,
          bCheckinDate: this.checkinDate,
          bCheckoutDate: this.checkoutDate,
          guestCount: availability.selectedGuestCount || this.guestCount,
          totalPrice: totalPrice
        };
  
        this.bookingRoomsService.addRoomtypeToBooking(bookingId, availability.seasonalRoomtypeId, bookingRoomtype).subscribe({
          next: (response) => {
            console.log(`BookingRoomtype added for seasonalRoomtypeId ${availability.roomtypeId}, bokingid - ${bookingId}:`, response);
          },
          error: (e) => {
            console.error(`Error adding BookingRoomtype for seasonalRoomtypeId ${availability.roomtypeId}:`, e);
          }
        });
      }
    }
  }

   //For loading existing parameters from real search to search-again
   onSearchParametersUpdated(criteria: { guestCount: number, checkinDate: string, checkoutDate: string }) {

    const formattedCheckinDate = this.formatDate(criteria.checkinDate);
    const formattedCheckoutDate = criteria.checkoutDate;

    this.guestCount = criteria.guestCount;
    this.checkinDate = formattedCheckinDate;
    this.checkoutDate = formattedCheckoutDate;
    this.loadAvailability();
  }


  //FORMAT DATE
  private formatDate(dateString: string): string {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
  }

  onNavigate(bookingId: number): void{
    this.router.navigate(['/showBooking', bookingId]);
    console.log('Booking id passed', bookingId);
  }

}

 // function calculateRoomPrice(unitPrice: number, maxAdults: number, guestCount: number): number {
  //   // Calculate the number of rooms required
  //   const requiredRooms = Math.ceil(guestCount / maxAdults);
  //   // Total price for the rooms
  //   return requiredRooms * unitPrice;
  // }

  // const unitPrice = 150; // Price per room
  // const maxAdults = 4; // Max adults per room

  // const guestCounts = [1, 2, 3, 4, 5];

  // guestCounts.forEach((guestCount) => {
  //     const totalPrice = calculateRoomPrice(unitPrice, maxAdults, guestCount);
  //     console.log(`Guest Count: ${guestCount}, Total Price: $${totalPrice}`);
  // });