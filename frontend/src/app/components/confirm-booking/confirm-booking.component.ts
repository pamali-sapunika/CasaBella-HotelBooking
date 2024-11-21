import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-confirm-booking',
  standalone: true,
  imports: [
  ],
  templateUrl: './confirm-booking.component.html',
  styleUrl: './confirm-booking.component.css'
})
export class ConfirmBookingComponent {

  constructor(private router: Router){}

  onNavigateBack(): void{
    // this.router.navigate(['../']);
    window.history.back();
  }

  onNavigateToMyBookings() {
    this.router.navigateByUrl('myBookings');
  }

}
