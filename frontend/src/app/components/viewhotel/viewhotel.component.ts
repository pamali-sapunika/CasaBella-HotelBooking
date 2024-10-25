import { Component, OnInit  } from '@angular/core';
import { Hotel } from '../../model/hotel.model';
import { HotelsService } from '../../services/hotelService/hotels.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-viewhotel',
  standalone: true,
  imports: [
    MatCardModule,
    CommonModule,
    MatButtonModule
  ],
  templateUrl: './viewhotel.component.html',
  styleUrl: './viewhotel.component.css'
})
export class ViewhotelComponent implements OnInit{

  hotelId!: number;
  hotel!: Hotel;

  constructor(private route: ActivatedRoute, private hotelService: HotelsService, private router: Router) { }

  ngOnInit(): void {
    this.hotelId = +this.route.snapshot.paramMap.get('hotelId')!;
    this.getHotelDetails();
  }

  getHotelDetails(): void {
    this.hotelService.getHotelById(this.hotelId).subscribe({
      next: (hotel) => {
        this.hotel = hotel;
      },
      error: (error) => {
        console.error('Error fetching single hotel data, error');
      }
    });
  }

  editHotel(): void{
    this.router.navigate(['edit-hotel', this.hotelId]);
  }

  deleteHotel(): void {
    this.hotelService.deleteHotel(this.hotelId).subscribe({
      next: () => {
        console.log('Hotel deleted successfully');
        this.router.navigateByUrl('hotellist')// Redirect to hotels list after deletion
      },
      error: (error) => {
        console.error('Error deleting hotel', error);
      }
    });
  }

  viewContracts(hotelId: number): void {
    this.router.navigate(['/hotel/hotelcontracts', hotelId]);
  }


  //menna meka wge hotel id eka pass krnw route eke
  navigateToAddContract(hotelId: number): void{
    this.router.navigate(['/addContract', hotelId])
  }


}
