import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [
    MatCardModule
  ],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})
export class AdminDashboardComponent {

  constructor(private router: Router){

  }

  navigateAddHotel() {  
    this.router.navigateByUrl('addHotel');
  }

  navigateViewHotel() {
    this.router.navigateByUrl('hotelslist');
  }

}
