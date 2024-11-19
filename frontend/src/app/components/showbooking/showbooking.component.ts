import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-showbooking',
  standalone: true,
  imports: [],
  templateUrl: './showbooking.component.html',
  styleUrl: './showbooking.component.css'
})
export class ShowbookingComponent implements OnInit{
  
  bookingId!: number;

  constructor(private router: Router, private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.bookingId = +this.route.snapshot.paramMap.get('bookingId')!;
    console.log('booking id found!', this.bookingId);
  }


}
