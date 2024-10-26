import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field'; 
import { MatInputModule } from '@angular/material/input'; 
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { Season } from '../../model/season.model';
import { SeasonService } from '../../services/seasonService/season.service';
import { SeasonalSupplement } from '../../model/seasonalSupplement.model';
import { SeasonalSupplementService } from '../../services/seasonalSupplementService/seasonal-supplement.service';
import { Supplement } from '../../model/supplement.model';
import { SupplementService } from '../../services/supplementService/supplement.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-addseasonalsupple',
  standalone: true,
  imports: [
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    CommonModule
  ],
  templateUrl: './addseasonalsupple.component.html',
  styleUrl: './addseasonalsupple.component.css'
})
export class AddseasonalsuppleComponent implements OnInit{

  @Input() contractId!: number;
  seasons: Season[] = [];
  supplements: Supplement[] = [];
  seasonalSupplementForm!: FormGroup;

  constructor( private seasonService: SeasonService, private supplementService: SupplementService, private seasonalSupplementService: SeasonalSupplementService, private fb: FormBuilder, private router: Router) {}

  ngOnInit(): void {
    console.log('Received contractId in AddseasonalroomsComponent:', this.contractId);
    this.fetchSeasons();
    this.fetchSupplements();
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

  fetchSupplements(): void {
    this.supplementService.getSupplements().subscribe({
      next: (supplements) => {
        this.supplements = supplements;
        console.log('supplements fetched:', this.supplements);
      },
      error: (error) => {
        console.error('Error fetching supplements:', error);
      }
    });
  }


  initializeForm(): void {
    this.seasonalSupplementForm = this.fb.group({
      seasonId: [null, Validators.required],
      supplementId: [null, Validators.required],
      pricePerUnit: [0, [Validators.required, Validators.min(0)]],
    });
  }

  onSubmit(): void {
    if (this.seasonalSupplementForm.valid) {

      const formValues = this.seasonalSupplementForm.value;
      const { seasonId, supplementId, pricePerUnit } = formValues;

      const requestBody = {
        pricePerUnit: parseFloat(formValues.pricePerUnit.toString())
      };

      this.seasonalSupplementService.addSupplementToSeason(supplementId, seasonId, requestBody).subscribe({
        next: () => {
          console.log('Seasonal Suppleemnt added successfully');
        },
        error: (error) => {
          console.error('Error adding seasonal suppelement:', error);
        }
      });
    } else {
      console.error('Form is invalid');
    }
  }


  onSelectSeasonName(season: Season): void {
    console.log("Season selected", season);
    this.seasonalSupplementForm.patchValue({
      seasonName: season.seasonName,
      seasonId: season.seasonId
    });
  }

  onSelectSupplementName(supplement: Supplement): void {
    console.log("Supplement selected", supplement);
    this.seasonalSupplementForm.patchValue({
      supplementName: supplement.supplementName,
      supplementId: supplement.supplementId
    });
  }

}
