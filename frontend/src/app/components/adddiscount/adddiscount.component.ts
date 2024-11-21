import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DiscountService } from '../../services/discountService/discount.service';
import { Discount } from '../../model/discount.model';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { Location } from '@angular/common';

@Component({
  selector: 'app-adddiscount',
  standalone: true,
  imports: [
    MatFormFieldModule, 
    MatInputModule, 
    MatCardModule, 
    MatButtonModule,
    ReactiveFormsModule,
  ],
  templateUrl: './adddiscount.component.html',
  styleUrl: './adddiscount.component.css'
})
export class AdddiscountComponent implements OnInit{

  discountForm: FormGroup;
  contractId!: number;

  constructor(
    private fb: FormBuilder,
    private discountService: DiscountService,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location
  ) {
    this.discountForm = this.fb.group({
      discountType: ['', Validators.required],
      description: ['', Validators.required],
      discountPercentage: ['', [Validators.required, Validators.min(0), Validators.max(100)]],
      daysPriorToArrival: ['', [Validators.required, Validators.min(0)]]
    });
  }

  ngOnInit(): void {
    // Capture the contract ID from the route
    this.route.params.subscribe(params => {
      this.contractId = +params['contractId'];
      console.log('Captured contractId:', this.contractId);
    });
  }

  onSubmit(): void {
    if (this.discountForm.valid) {
      const newDiscount: Discount = this.discountForm.value;

      this.discountService.addDiscount(newDiscount).subscribe({
        next: (response) => {
          // After creating the discount, assign it to the contract
          this.discountService.assignContractToDiscount(response.discountId, this.contractId).subscribe(() => {
            // Navigate to another page or show success
            this.router.navigate(['/showContract', this.contractId]);
            console.log('Discount created and assigned to contract successfully.');
            alert('Discount added successfully.');
          });
        },
        error: (error) => {
          console.error('Error creating discount', error);
        }
      });
    }
  }

  navigateBack(): void{
    this.location.back();
  }

}


// contractId!: number;
// disocountForm: FormGroup;

// constructor(private fb: FormBuilder, private discountService: DiscountService, private route: ActivatedRoute, private router: Router){
//   this.disocountForm = this.fb.group({
//     discountId: ['', Validators.required],
//     contractId: ['', Validators.required],
//     discountType: ['', Validators.required],
//     description: ['', Validators.required],
//     discountPercentage: ['', Validators.required],
//     daysPriorToArrival: ['', Validators.required],
//   });
// }

// ngOnInit(): void {
//   this.contractId = +this.route.snapshot.paramMap.get('contractId')!;
//   console.log('Captured contractId for dicount:', this.contractId);
// }

// onSubmit(): void{
//   if(this.disocountForm.valid){
//     const newDiscount: Discount = this.disocountForm.value;

//     this.discountService.addDiscount(newDiscount).subscribe({
//       next: (response) => {
//         this.discountService.assignContractToDiscount(response.discountId, this.contractId).subscribe(() => {
//           console.log('discount saved');
//         });
//       },
//       error: (error) => {
//         console.log('Error creating discount', error);
//       }
//     });
//   }
// }


