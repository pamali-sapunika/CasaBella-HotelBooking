import { Component, ChangeDetectionStrategy } from '@angular/core';
import {MatSelectModule} from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { Hotel } from '../../model/hotel.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HotelsService } from '../../services/hotelService/hotels.service';
import { Router } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-addhotel',
  standalone: true,
  imports: [
    MatFormFieldModule, 
    MatInputModule, 
    MatSelectModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    ReactiveFormsModule,
    CommonModule
  ],
  templateUrl: './addhotel.component.html',
  styleUrl: './addhotel.component.css'
})
export class AddhotelComponent {

  hotelForm: FormGroup;

  constructor(private fb: FormBuilder, private hotelService: HotelsService, private router: Router) {
    this.hotelForm = this.fb.group({
      hotelName: ['', Validators.required],
      hotelEmail: ['', [Validators.required, Validators.email]],
      city: ['', Validators.required],
      state: ['', Validators.required],
      country: ['', Validators.required],
      location: ['', Validators.required],
      hotelContact: ['', Validators.required],
      hotelPerson: ['', Validators.required],
      starRating: ['', [Validators.required, Validators.min(1), Validators.max(5)]],
      description: ['']
    });
  }

  onSubmit(): void {
    console.log('hotel creation method called');
    if (this.hotelForm.valid) {
      const newHotel: Hotel = this.hotelForm.value;
      this.hotelService.addHotel(newHotel).subscribe({
        next: (response) => {
          console.log('Hotel added successfully', response);
          this.router.navigateByUrl('hotelslist')// Redirect after successful addition
        },
        error: (error) => {
          console.error('Error adding hotel:', error);
        }
      });
    }
  }

}
