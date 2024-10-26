import { Component, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule  } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field'; 
import { MatInputModule } from '@angular/material/input'; 
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { AddseasonalroomsComponent } from '../addseasonalrooms/addseasonalrooms.component';
import { AddseasonalsuppleComponent } from '../addseasonalsupple/addseasonalsupple.component';

@Component({
  selector: 'app-seasonalitems',
  standalone: true,
  imports: [    
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    CommonModule,
    AddseasonalroomsComponent,
    AddseasonalsuppleComponent
  ],
  templateUrl: './seasonalitems.component.html',
  styleUrl: './seasonalitems.component.css'
})
export class SeasonalitemsComponent implements OnInit{

  contractId!: number;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute, 
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.contractId = +params['contractId'];
      console.log('Captured contractId in AddseasonalroomsComponent:', this.contractId);
    });
  }

}
