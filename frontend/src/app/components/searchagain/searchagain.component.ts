import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { AvailabilityDTO } from '../../model/availability.dto';
import { Router } from '@angular/router';
import { SeasonalRoomsService } from '../../services/seasonalRoomsService/seasonal-rooms.service';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';

@Component({
  selector: 'app-searchagain',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatInputModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule,
  ],
  templateUrl: './searchagain.component.html',
  styleUrl: './searchagain.component.css'
})
export class SearchagainComponent implements OnInit{

  searchForm: FormGroup;
  @Input() guestCount!: number;
  @Input() checkinDate!: string;
  @Input() checkoutDate!: string;
  @Output() searchParametersUpdated = new EventEmitter<{ guestCount: number, checkinDate: string, checkoutDate: string }>();

  constructor(private router: Router, private fb: FormBuilder, private seasonalRoomsService: SeasonalRoomsService){ 
    this.searchForm = this.fb.group({
      guestCount: [''],
      checkinDate: [''],
      checkoutDate: ['']
    });
  }

  
  ngOnInit(): void {
    this.searchForm.patchValue({
      guestCount: this.guestCount,
      checkinDate: this.checkinDate,
      checkoutDate: this.checkoutDate
    })
  }

  onSearch(){

    const formattedCheckinDate = this.formatDate(this.searchForm.value.checkinDate);
    const formattedCheckoutDate = this.formatDate(this.searchForm.value.checkoutDate);

    this.searchParametersUpdated.emit({
      guestCount: this.searchForm.value.guestCount,
      checkinDate: formattedCheckinDate,
      checkoutDate: formattedCheckoutDate
    });

    console.log('Search para updated successfully');
  }
  
  private formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
  }


}
