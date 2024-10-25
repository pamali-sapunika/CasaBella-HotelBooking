import { Component, OnInit, importProvidersFrom } from '@angular/core';
import { Contract } from '../../model/contract.model';
import { ContractService } from '../../services/contractService/contract.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute} from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-addcontract',
  standalone: true,
  imports: [
    MatFormFieldModule, 
    MatInputModule, 
    MatCardModule, 
    MatButtonModule,
    ReactiveFormsModule,
    CommonModule
  ],
  templateUrl: './addcontract.component.html',
  styleUrl: './addcontract.component.css'
})
export class AddcontractComponent implements OnInit {

  contractForm: FormGroup;
  hotelId!: number; // Variable to store the captured hotel ID

  constructor(
    private fb: FormBuilder,
    private contractService: ContractService,
    private route: ActivatedRoute, // Capture the route parameters
    private router: Router
  ) {
    this.contractForm = this.fb.group({
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      cancellationDeadlineDays: [null, [Validators.required, Validators.min(0)]],
      cancellationDescription: ['', Validators.required],
      cancellationFeePercentage: [null, [Validators.required, Validators.min(0), Validators.max(100)]],
      prepaymentFeePercenatage: [null, [Validators.required, Validators.min(0), Validators.max(100)]],
      balancedPaymentDays: [null, [Validators.required, Validators.min(0)]],
      balancedPaymentPercentage: [null, [Validators.required, Validators.min(0), Validators.max(100)]]
    });
  }

  ngOnInit(): void {
      //capture hitel id from route
      this.route.params.subscribe(params => {
        this.hotelId = +params['hotelId'];
        console.log('Captured hotelId:', this.hotelId);
      })
  }

  onSubmit(): void {
    if (this.contractForm.valid) {
      const newContract: Contract = this.contractForm.value;

      // Save the contract and assign the hotel ID
      this.contractService.addContract(newContract).subscribe({
        next: (response) => {
          // After adding the contract, assign the hotel to the contract
          this.contractService.assignHotelToContract(response.contractId, this.hotelId).subscribe(() => {
            // Navigate to addSeasons page with contractId as route parameter
            this.router.navigate(['/addSeasons', response.contractId]);
            console.log('Got contract id');
          });
        },
        error: (error) => {
          console.error('Error creating contract', error);
        }
      });
    }
  }



}
