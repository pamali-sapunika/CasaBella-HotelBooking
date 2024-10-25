import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Contract } from '../../model/contract.model';
import { ContractService } from '../../services/contractService/contract.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-hotel-contracts',
  standalone: true,
  imports: [
    MatTableModule,
    MatCardModule,
  ],
  templateUrl: './hotel-contracts.component.html',
  styleUrl: './hotel-contracts.component.css'
})
export class HotelContractsComponent implements OnInit  {

  hotelId!: number;
  contracts: Contract[] = [];
  dataSource: MatTableDataSource<Contract>;

  constructor(
    private route: ActivatedRoute,
    private contractService: ContractService
  ) {
    this.dataSource = new MatTableDataSource<Contract>();
  }

  displayedColumns: string[] = [
    'contractId',
    'startDate', 
    'endDate', 
    'cancellationDescription', 
    'cancellationFeePercentage', 
    'prepaymentFeePercenatage', 
    'balancedPaymentDays', 
    'actions'
  ];

  ngOnInit(): void {
    this.hotelId = +this.route.snapshot.paramMap.get('hotelId')!;
    this.getContractsByHotelId();
  }

  getContractsByHotelId(): void {
    this.contractService.getContractsByHotelId(this.hotelId).subscribe({
      next: (contracts) => {
        this.contracts = contracts;
        this.dataSource.data = contracts; // Update the data source
      },
      error: (error) => {
        console.error('Error fetching contracts:', error);
      }
    });
  }

}
