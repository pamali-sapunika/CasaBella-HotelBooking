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
import { SeasonalSupplement } from '../../model/seasonalSupplement.model';
import { AvaialbilitySupplements } from '../../model/availabilitySupple.dto';
import { BookingroomsService } from '../../services/bookingRoomtypeService/bookingrooms.service';
import { BookingsupplementsService } from '../../services/bookingSupplementService/bookingsupplements.service';
import { BookingService } from '../../services/bookingService/booking.service';
import { Booking } from '../../model/booking.model';
import { BookingRoomtypes } from '../../model/bookingrooms.model';
import { BookingSupplements } from '../../model/bookingsupplement.model';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-availability',
  standalone: true,
  imports: [
    MatCardModule,
    MatButtonModule,
    CommonModule,
    SearchagainComponent,
    FormsModule
  ],
  templateUrl: './availability.component.html',
  styleUrl: './availability.component.css'
})
export class AvailabilityComponent implements OnInit{

  availabilityList: AvailabilityDTO[] = [];
  seasonalSupplements: AvaialbilitySupplements[] = []; 
  hotelId!: number; 
  guestCount!: number; 
  checkinDate!: string;
  checkoutDate!: string;

  existingBookingId: number | null = null;

  constructor(
    private router: Router, 
    private route: ActivatedRoute, 
    private hotelService: HotelsService, 
    private seasonalRoomsService: SeasonalRoomsService,
    private seasonalSupplementService: SeasonalSupplementService,
    private bookingRoomsService: BookingroomsService,
    private bookingSupplementService: BookingsupplementsService,
    private bookingService: BookingService
  ){ }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.hotelId = Number(params.get('hotelId'));
    });
    this.route.queryParamMap.subscribe(params => {
      this.guestCount = Number(params.get('guestCount'));
      this.checkinDate = this.formatDate(params.get('checkinDate')!);
      this.checkoutDate = this.formatDate(params.get('checkoutDate')!);
    });
    this.loadAvailability();
  }

  loadAvailability(): void{
    if (this.hotelId  && this.guestCount && this.checkinDate && this.checkoutDate !== undefined) {
      this.seasonalRoomsService.getAvailability(this.hotelId, this.guestCount, this.checkinDate, this.checkoutDate).subscribe({
        next: (data) => {
          this.availabilityList = data;
          console.log('Availability fetched successfullly');
          if (data.length > 0) {
            const seasonId = data[0].seasonId; 
            this.fetchSeasonalSupplements(seasonId);
          }
        },
        error: (e) => {
          console.error('Error fetching availabilty', e);
        }
      });
    }
  }

  onSubmit(): void {
    if (this.existingBookingId) {
      console.log(`Using existing booking with ID: ${this.existingBookingId}`);
      this.addMultipleBookingRoomtypes(this.existingBookingId);
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
          console.log('New booking created:', booking);
          this.addMultipleBookingRoomtypes(booking.bookingId);
        },
        error: (e) => {
          console.error('Error creating booking:', e);
        }
      });
    }
  }

  // addBookingRoomtype(bookingId: number, seasonalRoomtypeId: number): void {
  //   const bookingRoomtype: BookingRoomtypes = {
  //     bookingRoomtypeId: 0,
  //     bookingId: bookingId,
  //     seasonalRoomtypeId: seasonalRoomtypeId,
  //     noOfRooms: 0,
  //     price: 0, 
  //     bCheckinDate: '',
  //     bCheckoutDate: '',
  //     guestCount: 0 
  //   };

  //   this.bookingRoomsService.addRoomtypeToBooking(bookingId, seasonalRoomtypeId, bookingRoomtype).subscribe({
  //     next: (response) => {
  //       console.log('BookingRoomtype added successfully:', response);
  //     },
  //     error: (e) => {
  //       console.error('Error adding BookingRoomtype:', e);
  //     }
  //   });
  // }

  // addBookingSupplement(bookingId: number, seasonalSupplementId: number): void {
  //   const bookingSupplement: BookingSupplements = {
  //     bookingSupplementId: 0,
  //     bookingId: bookingId,
  //     seasonalSupplementId: seasonalSupplementId,
  //     quantity: 0, 
  //     pricePerUnit: 0 
  //   };

  //   this.bookingSupplementService.addSupplementToBooking(bookingId, seasonalSupplementId, bookingSupplement).subscribe({
  //     next: (response) => {
  //       console.log('BookingSupplement added successfully:', response);
  //     },
  //     error: (e) => {
  //       console.error('Error adding BookingSupplement:', e);
  //     }
  //   });
  // }

  addMultipleBookingRoomtypes(bookingId: number): void {
    for (const availability of this.availabilityList) {
      if (availability.selectedRooms && availability.selectedRooms > 0) {
        const bookingRoomtype: BookingRoomtypes = {
          bookingRoomtypeId: 0,
          bookingId: bookingId,
          seasonalRoomtypeId: availability.roomtypeId, // Assuming roomtypeId is the same as seasonalRoomtypeId
          noOfRooms: availability.selectedRooms,
          price: availability.price,
          bCheckinDate: this.checkinDate,
          bCheckoutDate: this.checkoutDate,
          guestCount: availability.selectedGuestCount || 0 // Optional guest count input
        };
  
        this.bookingRoomsService.addRoomtypeToBooking(bookingId, availability.roomtypeId, bookingRoomtype).subscribe({
          next: (response) => {
            console.log(`BookingRoomtype added for seasonalRoomtypeId ${availability.roomtypeId}:`, response);
          },
          error: (e) => {
            console.error(`Error adding BookingRoomtype for seasonalRoomtypeId ${availability.roomtypeId}:`, e);
          }
        });
      }
    }
  }


  fetchSeasonalSupplements(seasonId: number): void {
    this.seasonalSupplementService.getSeasonalSupplementsWithNames(seasonId).subscribe({
      next: (supplements) => {
        this.seasonalSupplements = supplements;
        console.log('P Seasonal supplements fetched successfully', this.seasonalSupplements);
      },
      error: (e) => {
        console.error('pP Error fetching seasonal supplements', e);
      }
    });
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