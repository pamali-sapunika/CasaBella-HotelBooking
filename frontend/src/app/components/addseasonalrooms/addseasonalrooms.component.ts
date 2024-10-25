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

  contractId!: number;
  seasonalRoomForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute, 
    private seasonService: SeasonService,
    private roomService: RoomService 
  ) {
    this.seasonalRoomForm = this.fb.group({
      roomType: ['', Validators.required],
      // Other form fields as needed
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.contractId = +params['contractId'];
      console.log('Captured contractId in AddseasonalroomsComponent:', this.contractId);
    });
  }









}
