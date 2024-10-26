import { Component, OnInit, Input} from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule  } from '@angular/forms';
import { Observable } from 'rxjs';
import { Season } from '../../model/season.model';
import { Roomtype } from '../../model/room.model';
import { SeasonalRooms } from '../../model/seasonalRoom.model';
import { SeasonalRoomsService } from '../../services/seasonalRoomsService/seasonal-rooms.service';
import { SeasonService } from '../../services/seasonService/season.service';
import { RoomService } from '../../services/roomtypeService/room.service';
import { MatFormFieldModule } from '@angular/material/form-field'; 
import { MatInputModule } from '@angular/material/input'; 
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-addseasonalrooms',
  standalone: true,
  imports: [
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    CommonModule
  ],
  templateUrl: './addseasonalrooms.component.html',
  styleUrl: './addseasonalrooms.component.css'
})
export class AddseasonalroomsComponent implements OnInit{

  @Input() contractId!: number;
  seasons: Season[] = [];
  roomtypes: Roomtype[] = [];
  seasonalRoomForm!: FormGroup;

  constructor( private seasonService: SeasonService, private roomService: RoomService, private seasonalRoomsService: SeasonalRoomsService, private fb: FormBuilder, private router: Router) {}

  ngOnInit(): void {
    console.log('Received contractId in AddseasonalroomsComponent:', this.contractId);
    this.fetchSeasons();
    this.fetchRoomtypes();
    this.initializeForm();
  }


  fetchSeasons(): void {
    console.log("Method called");
    this.seasonService.getSeasonsByContractId(this.contractId).subscribe({
      next: (seasons) => {
        this.seasons = seasons;
        console.log('Seasons fetched Pamali:', this.seasons);
      },
      error: (error) => {
        console.error('Error fetching seasons:', error);
      }
    });
  }

  fetchRoomtypes(): void {
    this.roomService.getRoomtypes().subscribe({
      next: (roomtypes) => {
        this.roomtypes = roomtypes;
        console.log('Roomtypes fetched Pamali:', this.roomtypes);
      },
      error: (error) => {
        console.error('Error fetching roomtypes:', error);
      }
    });
  }


  initializeForm(): void {
    this.seasonalRoomForm = this.fb.group({
      seasonId: [null, Validators.required],
      roomtypeId: [null, Validators.required],
      hotelId: [null, Validators.required],
      price: [0, [Validators.required, Validators.min(0)]],
      noofRooms: [0, [Validators.required, Validators.min(0)]],
      maxAdults: [0, [Validators.required, Validators.min(0)]],
      noofReservedRooms: [0, [Validators.required, Validators.min(0)]]
    });
  }

  onSubmit(): void {
    if (this.seasonalRoomForm.valid) {
      const formValues = this.seasonalRoomForm.value;
      const { seasonId, roomtypeId, hotelId, price, noofRooms, maxAdults, noofReservedRooms } = formValues;
  
      const requestBody = {
        price: parseFloat(formValues.price.toString()), 
        noofRooms: parseInt(formValues.noofRooms.toString(), 10), 
        maxAdults: parseInt(formValues.maxAdults.toString(), 10), 
        noofReservedRooms: parseInt(formValues.noofReservedRooms.toString(), 10) 
      };
  
      this.seasonalRoomsService.addRoomtypeToSeason(roomtypeId, seasonId, requestBody).subscribe({
        next: (response) => {
          console.log('Seasonal room type added successfully');
  
          this.seasonalRoomsService.assignHotelToSeasonalRoomtype(response.seasonalRoomtypeId, hotelId).subscribe({
            next: () => {
              console.log('Hotel assigned to seasonalroom type successfully!');
            },
            error: (error) => {
              console.error('Error assigning hotel to seasonal room type:', error);
            }
          });
        },
        error: (error) => {
          console.error('Error adding seasonal room type:', error);
        }
      });
    } else {
      console.error('Form is invalid');
    }
  }

  onSelectSeasonName(season: Season): void {
    console.log("Season selected", season);
    this.seasonalRoomForm.patchValue({
      seasonName: season.seasonName,
      seasonId: season.seasonId
    });
  }

  onSelectRoomtypeName(roomtype: Roomtype): void {
    console.log("Roomtype selected", roomtype);
    this.seasonalRoomForm.patchValue({
      roomtypeName: roomtype.roomtypeName,
      roomtypeId: roomtype.roomtypeId
    });
  }

 

}



 // @Input() contractId!: number;
  // seasonalRoomForm!: FormGroup;

  // constructor( private seasonalRoomsService: SeasonalRoomsService, private fb: FormBuilder, private router: Router) {}

  // ngOnInit(): void {
  //   console.log('Received contractId in AddseasonalroomsComponent:', this.contractId);
  //   this.initializeForm();
  // }


  // initializeForm(): void {
  //   this.seasonalRoomForm = this.fb.group({
  //     seasonId: [null, Validators.required],
  //     roomtypeId: [null, Validators.required],
  //     hotelId: [null, Validators.required],
  //     price: [0, [Validators.required, Validators.min(0)]],
  //     noofRooms: [0, [Validators.required, Validators.min(0)]],
  //     maxAdults: [0, [Validators.required, Validators.min(0)]],
  //     noofReservedRooms: [0, [Validators.required, Validators.min(0)]]
  //   });
  // }

  // onSubmit(): void {
  //   if (this.seasonalRoomForm.valid) {
  //     const formData = this.seasonalRoomForm.value;

  //     const requestBody = {
  //       price: parseFloat(formData.price.toString()), // Ensure price is a double
  //       noofRooms: parseInt(formData.noofRooms.toString(), 10), // Ensure noofRooms is an int
  //       maxAdults: parseInt(formData.maxAdults.toString(), 10), // Ensure maxAdults is an int
  //       noofReservedRooms: parseInt(formData.noofReservedRooms.toString(), 10) // Ensure noofReservedRooms is an int
  //     };

  //     this.seasonalRoomsService.addRoomtypeToSeason(formData.roomtypeId, formData.seasonId, requestBody).subscribe({
  //       next: () => {
  //         console.log('Seasonal room type added successfully');
  //       },
  //       error: (error) => {
  //         console.error('Error adding seasonal room type:', error);
  //       }
  //     });
  //   }
  // }



  // onSubmit(): void {
  //   if (this.seasonalRoomForm.valid) {

  //     const formValues = this.seasonalRoomForm.value;
  //     const { seasonId, roomtypeId, price, noofRooms, maxAdults, noofReservedRooms } = formValues;

  //     const requestBody = {
  //       price: parseFloat(formValues.price.toString()), // Ensure price is a double
  //       noofRooms: parseInt(formValues.noofRooms.toString(), 10), 
  //       maxAdults: parseInt(formValues.maxAdults.toString(), 10), 
  //       noofReservedRooms: parseInt(formValues.noofReservedRooms.toString(), 10) 
  //     };

  //     this.seasonalRoomsService.addRoomtypeToSeason(roomtypeId, seasonId, requestBody).subscribe({
  //       next: () => {
  //         console.log('Seasonal room type added successfully');
  //       },
  //       error: (error) => {
  //         console.error('Error adding seasonal room type:', error);
  //       }
  //     });
  //   } else {
  //     console.error('Form is invalid');
  //   }
  // }

