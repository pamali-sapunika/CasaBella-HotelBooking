import { Component, OnInit } from '@angular/core';
import { Contract } from '../../model/contract.model';
import { ContractService } from '../../services/contractService/contract.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-shownewcontract',
  standalone: true,
  imports: [
    CommonModule,

  ],
  templateUrl: './shownewcontract.component.html',
  styleUrl: './shownewcontract.component.css'
})
export class ShownewcontractComponent implements OnInit{

  contractId!: number;
  contractDetails: any;

  constructor(
    private contractService: ContractService,
    private route: ActivatedRoute,
    private router: Router
  ){}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.contractId = +params['contractId'];
      console.log('contractId found', this.contractId);
      if(this.contractId){
        this.fetchContract(this.contractId);
      }
    })
  }

  fetchContract(contractId: number) {
    this.contractService.getContractById(contractId).subscribe({
      next: (response) => {
        this.contractDetails = response;
        console.log('Contract fetched succesfully', response);
      },
      error: (e) => {
        console.error('Error fetching contract ', contractId);
      }
    })
  }

  onNavigate(){
    this.router.navigate(['confirmContract', this.contractId]);
    console.log('Navigated to congradulations contract', this.contractId);
  }

  

}
