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

@Component({
  selector: 'app-availability',
  standalone: true,
  imports: [
    MatCardModule,
    MatButtonModule,
    CommonModule,
    SearchagainComponent
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

  constructor(
    private router: Router, 
    private route: ActivatedRoute, 
    private hotelService: HotelsService, 
    private seasonalRoomsService: SeasonalRoomsService,
    private seasonalSupplementService: SeasonalSupplementService
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

  private formatDate(dateString: string): string {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
  }

}
