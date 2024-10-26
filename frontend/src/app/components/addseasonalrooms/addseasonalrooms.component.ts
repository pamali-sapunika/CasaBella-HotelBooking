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

  constructor( private seasonService: SeasonService, private roomService: RoomService, private fb: FormBuilder) {}

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
      seasonalRoomtypeId: [null, Validators.required],
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
      const seasonalRoomData = this.seasonalRoomForm.value;
      console.log('Form submitted with:', seasonalRoomData);
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

  // onSelectRoomtypeName(roomtype: Roomtype): void {
  //   console.log("Room type selected:", roomtype); 
  //   this.seasonalRoomForm.patchValue({
  //     roomtypeId: roomtype.roomtypeId
  //   });
  // }














  // seasonalRoomForm: FormGroup;

  // constructor(
  //   private fb: FormBuilder,
  //   private route: ActivatedRoute, 
  //   private seasonService: SeasonService,
  //   private roomService: RoomService 
  // ) {
  //   this.seasonalRoomForm = this.fb.group({
  //     roomType: ['', Validators.required],
  //     // Other form fields as needed
  //   });
  // }

  // ngOnInit(): void {
  //   this.route.params.subscribe(params => {
  //     this.contractId = +params['contractId'];
  //     console.log('Captured contractId in AddseasonalroomsComponent:', this.contractId);
  //   });
  // }


}
