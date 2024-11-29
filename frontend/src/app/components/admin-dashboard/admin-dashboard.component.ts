import { Component, OnInit } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { Router } from '@angular/router';
import { UserService } from '../../services/userService/user.service';
import { User } from '../../model/user.model';
import { AuthService } from '../../services/authService/auth.service';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [
    MatCardModule
  ],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})
export class AdminDashboardComponent implements OnInit{

  constructor(private router: Router, private userService: UserService, private authService: AuthService){}

  userId: string | null = null;
  userDetails: User | null = null;

  ngOnInit(): void {
    this.userId= this.authService.getUserIdFromToken();
    console.log('User ID from token:', this.userId);
    if (this.userId) {
      // If userId is available, fetch user details by username or userId
      this.fetchUser(this.userId);
    }
  }

  fetchUser(userId: string): void {
    this.userService.getUserByUsername(userId).subscribe({
      next: (user: User) => {
        this.userDetails = user;
        console.log('Fetched user details:', user);
  
        const userRole = user.role;
        console.log('User role:', userRole);
  
        // Redirect to login if the user is not an ADMIN
        if (userRole !== 'ADMIN') {
          
          this.router.navigateByUrl('home');
          alert('Login Failed. You are logged in as a user. Redirecting to home.');
          // this.router.navigateByUrl('login');
        }
      },
      error: (error) => {
        console.error('Error fetching user details:', error);
        this.router.navigateByUrl('/login'); // Redirect to login on error
      }
    });
  }
  

  navigateAddHotel() {  
    this.router.navigateByUrl('addHotel');
  }

  navigateViewHotel() {
    this.router.navigateByUrl('hotelslist');
  }

}
