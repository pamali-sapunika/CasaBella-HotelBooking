import { Component } from '@angular/core';
import { User } from '../../model/user.model';
import { Router } from '@angular/router';
import { UserService } from '../../services/userService/user.service';
import { AuthService } from '../../services/authService/auth.service';
import { Booking } from '../../model/booking.model';
import { BookingService } from '../../services/bookingService/booking.service';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-mybookings',
  standalone: true,
  imports: [
    MatCardModule,
    CommonModule
  ],
  templateUrl: './mybookings.component.html',
  styleUrl: './mybookings.component.css'
})
export class MybookingsComponent {

  constructor(
    private router: Router,
    private userService: UserService,
    private bookingService: BookingService,
    private authService: AuthService
  ) {}

  username: string | null = null; // To hold the username retrieved from the token
  userDetails: User | null = null; // To store user details
  bookings: Booking[] = []; // Array to store bookings
  loading: boolean = true; // To manage loading state
  errorMessage: string | null = null; // To store error messages

  ngOnInit(): void {
    this.username = this.authService.getUserIdFromToken(); // Get the username from token
    console.log('Username from token:', this.username);

    if (this.username) {
      // Fetch user details using the username
      this.fetchUser(this.username);
    } else {
      // Redirect to login if no username is found
      this.router.navigateByUrl('login');
    }
  }

  goToPage(): void {
    this.router.navigateByUrl('hotellist'); // Navigate to hotel list page
  }

  fetchUser(username: string): void {
    this.userService.getUserByUsername(username).subscribe({
      next: (user: User) => {
        this.userDetails = user; // Store the user details
        console.log('Fetched user details successfully:', user);

        if (this.userDetails?.userId) {
          console.log('User ID from fetched user details:', this.userDetails.userId);

          // Fetch bookings after user details are successfully retrieved
          this.fetchBookings(this.userDetails.userId);
        } else {
          console.warn('User ID is not found in the fetched user details.');
          this.loading = false;
          this.errorMessage = 'User ID not found.';
        }
      },
      error: (error) => {
        console.error('Error fetching user details:', error);
        this.loading = false;
        this.errorMessage = 'Error fetching user details.';
      },
      complete: () => {
        console.log('fetchUser call completed.');
      }
    });
  }

  fetchBookings(userId: number): void {
    console.log('Fetching bookings for User ID:', userId);

    this.bookingService.getBookingsByUserId(userId).subscribe({
      next: (bookings: Booking[]) => {
        this.bookings = bookings; // Store the bookings for the user
        this.loading = false; // Set loading to false after data is fetched
        console.log('Bookings fetched:', bookings);
      },
      error: (error) => {
        console.error('Error fetching bookings:', error);
        this.errorMessage = 'There was an error fetching your bookings.'; // Set error message
        this.loading = false; // Set loading to false even in case of error
      }
    });
  }
}
