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
import {MatCheckboxModule} from '@angular/material/checkbox';

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

  hotelId!: number; 
  guestCount!: number; 
  checkinDate!: string;
  checkoutDate!: string;

  existingBookingId: number | null = null;
  noOfNights: number = 0;
  guestCounts: number[] = [1, 2, 3, 4, 5];
  unitPrice: number = 150;  // Price per room (e.g. $150)
  maxAdults: number = 4;  

  constructor(
    private router: Router, 
    private route: ActivatedRoute, 
    private hotelService: HotelsService, 
    private seasonalRoomsService: SeasonalRoomsService,
    private seasonalSupplementService: SeasonalSupplementService,
    private bookingRoomsService: BookingroomsService,
    private bookingSupplementService: BookingsupplementsService,
    private bookingService: BookingService
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
    this.guestCounts.forEach((guestCount) => {
      const totalPrice = this.calculateRoomPrice(this.unitPrice, this.maxAdults, guestCount, this.noOfNights);
      console.log(`Guest Count: ${guestCount}, Total Price for ${this.noOfNights} nights: $${totalPrice}`);
    });
    this.loadAvailability();
  }

  loadAvailability(): void {
    if (this.hotelId && this.guestCount && this.checkinDate && this.checkoutDate !== undefined) {
      this.seasonalRoomsService.getAvailability(this.hotelId, this.guestCount, this.checkinDate, this.checkoutDate).subscribe({
        next: (data) => {
          this.availabilityList = data;
  
          // Initialize selectedGuestCount with the guestCount value
          this.availabilityList.forEach((availability) => {
            availability.selectedGuestCount = this.guestCount; // Use the default guest count
          });
  
          this.fetchSupplements();
          this.calculateNoofNights();
        },
        error: (e) => {
          console.error('Error fetching availability', e);
        }
      });
    }
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

  calculateRoomPrice(unitPrice: number, noOfRooms: number, noOfNights: number, markupPercentage: number): number {
    const basePrice = unitPrice * noOfRooms * noOfNights;

    const totalPrice = basePrice * (1 + markupPercentage / 100);
  
    return totalPrice;
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
        status: 'new'
      };
  
      this.bookingService.addBooking(newBooking).subscribe({
        next: (booking) => {
          this.existingBookingId = booking.bookingId;
          console.log(`New booking created: ${booking.bookingId}`, booking);
          this.addMultipleBookingRoomtypes(booking.bookingId);
          this.addSupplementsToBooking(booking.bookingId); 
        },
        error: (e) => {
          console.error('Error creating booking:', e);
        }
      });
    }
  }

  addSupplementsToBooking(bookingId: number): void {
    for (const availability of this.availabilityList) {
      const seasonalRoomtypeId = availability.seasonalRoomtypeId;
      const supplements = this.supplementCache[seasonalRoomtypeId];
  
      if (supplements) {
        supplements.forEach(supplement => {
          if (supplement.selectedQuantity && supplement.selectedQuantity > 0) {
            const bookingSupplement: BookingSupplements = {
              bookingSupplementId: 0,
              bookingId: bookingId,
              seasonalSupplementId: supplement.seasonalSupplementId,
              quantity: supplement.selectedQuantity,
              pricePerUnit: supplement.pricePerUnit
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
        const bookingRoomtype: BookingRoomtypes = {
          bookingRoomtypeId: 0,
          bookingId: bookingId,
          seasonalRoomtypeId: availability.roomtypeId,
          noOfRooms: availability.selectedRooms,
          price: availability.price,
          bCheckinDate: this.checkinDate,
          bCheckoutDate: this.checkoutDate,
          guestCount: availability.selectedGuestCount || this.guestCount
        };
  
        this.bookingRoomsService.addRoomtypeToBooking(bookingId, availability.roomtypeId, bookingRoomtype).subscribe({
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

}

 // function calculateRoomPrice(unitPrice: number, maxAdults: number, guestCount: number): number {
  //   // Calculate the number of rooms required
  //   const requiredRooms = Math.ceil(guestCount / maxAdults);
  //   // Total price for the rooms
  //   return requiredRooms * unitPrice;
  // }

  // // Example Usage
  // const unitPrice = 150; // Price per room
  // const maxAdults = 4; // Max adults per room

  // // Simulate guest count inputs
  // const guestCounts = [1, 2, 3, 4, 5];

  // guestCounts.forEach((guestCount) => {
  //     const totalPrice = calculateRoomPrice(unitPrice, maxAdults, guestCount);
  //     console.log(`Guest Count: ${guestCount}, Total Price: $${totalPrice}`);
  // });