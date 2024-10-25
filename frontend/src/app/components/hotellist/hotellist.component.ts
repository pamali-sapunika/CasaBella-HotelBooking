import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Hotel } from '../../model/hotel.model';
import { HotelsService } from '../../services/hotelService/hotels.service';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatTableDataSource } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';  // Import MatCardModule
import { MatToolbarModule } from '@angular/material/toolbar'; // Optional: Import for better header styling
import { MatDividerModule } from '@angular/material/divider';

@Component({
  selector: 'app-hotellist',
  standalone: true,
  imports: [
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    MatCardModule,
    MatToolbarModule,
    MatDividerModule,
  ],
  templateUrl: './hotellist.component.html',
  styleUrl: './hotellist.component.css'
})
export class HotellistComponent implements OnInit {

  displayedColumns: string[] = ['hotelId', 'hotelName','hotelEmail', 'city','hotelContact', 'starRating', 'actions'];
  dataSource: MatTableDataSource<Hotel>;

  constructor(private hotelService: HotelsService, private router: Router) {
    this.dataSource = new MatTableDataSource<Hotel>();
  }

  ngOnInit(): void {
    this.getHotels();
  }

  getHotels(): void {
    this.hotelService.getHotels().subscribe({
      next: (hotels) => {
        this.dataSource.data = hotels;
        console.log(this.dataSource);
      },
      error: (error) => {
        console.error('Error fetching hotels:', error);
      }
    });
  }

  editHotel(hotelId: number): void {
    console.log('Editing hotel with ID:', hotelId);
    // Add routing or open modal for editing
  }

  deleteHotel(hotelId: number): void {
    this.hotelService.deleteHotel(hotelId).subscribe({
      next: () => {
        this.getHotels(); // Refresh the hotel list after deletion
      },
      error: (error) => {
        console.error('Error deleting hotel:', error);
      }
    });
  }

  // Method to navigate to the contracts of the selected hotel
  viewHotel(hotelId: number): void {
    this.router.navigate(['/hotel', hotelId]);
  }

  navigateAddHotel(): void{
    this.router.navigateByUrl('addHotel');
  }

  // navigateAddContract(): void{
  //   this.router.navigateByUrl('addContract');
  // }



}
