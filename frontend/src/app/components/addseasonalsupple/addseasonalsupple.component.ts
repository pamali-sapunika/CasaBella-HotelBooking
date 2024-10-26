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

  constructor(
    private fb: FormBuilder,
    private seasonService: SeasonService,
    private supplementService: SupplementService
  ) {}

  ngOnInit(): void {
    console.log('Received contractId in AddSeasonalSupplementComponent:', this.contractId);
    this.fetchSeasons();
    this.fetchSupplements();
    this.initializeForm();
  }

  fetchSeasons(): void {
    this.seasonService.getSeasonsByContractId(this.contractId).subscribe({
      next: (seasons) => {
        this.seasons = seasons;
        console.log('Seasons fetched:', this.seasons);
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
        console.log('Supplements fetched:', this.supplements);
      },
      error: (error) => {
        console.error('Error fetching supplements:', error);
      }
    });
  }

  initializeForm(): void {
    this.seasonalSupplementForm = this.fb.group({
      seasonalSupplementId: [null],
      seasonId: [null, Validators.required],
      supplementId: [null, Validators.required],
      pricePerUnit: [0, [Validators.required, Validators.min(0)]]
    });
  }

  onSubmit(): void {
    if (this.seasonalSupplementForm.valid) {
      const seasonalSupplementData: SeasonalSupplement = this.seasonalSupplementForm.value;
      console.log('Form submitted with:', seasonalSupplementData);
      // Add logic to send data to the server
    } else {
      console.error('Form is invalid');
    }
  }

  onSelectSeason(season: Season): void {
    console.log("Selected season", season);
    this.seasonalSupplementForm.patchValue({
      seasonId: season.seasonId
    });
  }

  onSelectSupplement(supplement: Supplement): void {
    console.log("Selected supplement", supplement);
    this.seasonalSupplementForm.patchValue({
      supplementId: supplement.supplementId
    });
  }


}
