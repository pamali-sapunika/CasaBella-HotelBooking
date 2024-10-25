import { Component } from '@angular/core';
import { Season } from '../../model/season.model';
import { SeasonService } from '../../services/seasonService/season.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute} from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-seasons',
  standalone: true,
  imports: [
    MatFormFieldModule, 
    MatInputModule, 
    MatCardModule, 
    MatButtonModule,
    ReactiveFormsModule,
    CommonModule
  ],
  templateUrl: './add-seasons.component.html',
  styleUrl: './add-seasons.component.css'
})
export class AddSeasonsComponent {

  seasonForm: FormGroup;
  contractId!: number;

  constructor(
    private fb: FormBuilder,
    private seasonService: SeasonService,
    private route: ActivatedRoute, // To capture route parameters
    private router: Router
  ) {
    this.seasonForm = this.fb.group({
      seasonName: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      markupPercentage: [null, [Validators.required, Validators.min(0), Validators.max(100)]]
    });
  }

  ngOnInit(): void {
    // Capture the contractId from the route parameter
    this.route.params.subscribe(params => {
      this.contractId = +params['contractId'];
      console.log('Captured contractId from previous page:', this.contractId);
    });
  }


  onSubmit(): void {
    if (this.seasonForm.valid) {
      const newSeason: Season = this.seasonForm.value;

      // Call the service to add the season
      this.seasonService.addSeason(newSeason).subscribe({
        next: (response) => {
          this.seasonService.assignContractToSeason(response.seasonId, this.contractId).subscribe(() =>{
            console.log('Season added');
          })
        },
        error: (error) => {
          console.error('Error adding season', error);
        }
      });
    }
  }

  navigateToAddSeasonalRooms(): void {
    this.router.navigate(['addSeasonalRooms', this.contractId]);
    console.log('Navigating to Add Seasonal Rooms with contractId:', this.contractId);
  }


}
