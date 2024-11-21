import { Component, OnInit } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';
import { SearchComponent } from '../search/search.component';
import { AuthService } from '../../services/authService/auth.service';
import { User } from '../../model/user.model';
import { UserService } from '../../services/userService/user.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    MatCardModule,
    MatToolbarModule,
    MatButtonModule,
    SearchComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{

  constructor(private router: Router, private userService: UserService, private authService: AuthService) {}

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

  goToPage(): void {
    this.router.navigateByUrl('hotellist');
  }

  fetchUser(userId: string): void {
    this.userService.getUserByUsername(userId).subscribe(
      (user: User) => {
        this.userDetails = user;  // Store the user details in the component
        console.log('Fetched user details:', user);
      },
      (error) => {
        console.error('Error fetching user details:', error);
      }
    );
  }




}
