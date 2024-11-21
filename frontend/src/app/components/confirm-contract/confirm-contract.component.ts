import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-confirm-contract',
  standalone: true,
  imports: [],
  templateUrl: './confirm-contract.component.html',
  styleUrl: './confirm-contract.component.css'
})
export class ConfirmContractComponent implements OnInit{

  contractId!: number;

  constructor(private router: Router, private route: ActivatedRoute){}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params =>{
      this.contractId = Number(params.get('contractId'));
    })
  }

  onNavigateBack(): void{
    // this.router.navigate(['../']);
    window.history.back();
  }

  onNavigateToHotelList(){
    this.router.navigateByUrl('hotelslist');
  }
  
}
