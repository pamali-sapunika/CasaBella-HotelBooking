import { Component, OnInit } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { ReactiveFormsModule } from '@angular/forms';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HotelsService } from '../../services/hotelService/hotels.service';
import { Router } from '@angular/router';
import { Hotel } from '../../model/hotel.model';
import { CommonModule } from '@angular/common';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';


@Component({
  selector: 'app-search',
  standalone: true,
  imports: [ 
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    ReactiveFormsModule,
    CommonModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatCardModule
  ],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent{

  searchForm: FormGroup;
  resultHotels: Hotel[] = [];

  constructor(private router: Router, private fb: FormBuilder, private hotelService: HotelsService){ 
    this.searchForm = this.fb.group({
      guestCount: [''],
      checkinDate: [''],
      checkoutDate: ['']
    });
  }

  private formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Months are 0-based
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
  }
  
  onSearch() {
    const { guestCount, checkinDate, checkoutDate } = this.searchForm.value;

    const formattedCheckinDate = this.formatDate(checkinDate);
    const formattedCheckoutDate = this.formatDate(checkoutDate);

    this.hotelService.searchHotels(guestCount, formattedCheckinDate, formattedCheckoutDate).subscribe({
      next: (hotels) => {
        this.resultHotels = hotels;
      },
      error: (error) => {
        console.error('Error fetching hotels:', error);
      }
    });
  }

  viewDetails(hotelId: number): void{
    this.router.navigate(['availableHotel', hotelId])
  }
  


}