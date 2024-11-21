import { Component } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { AuthService } from '../../services/authService/auth.service';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    RouterOutlet,
    RouterLink,
    CommonModule
  ],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css'
})
export class LayoutComponent {

  isLoggedIn: boolean = false;  

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isAuthenticated();
    console.log('Is user logged in', this.isLoggedIn); 
  }

  onLogout(): void {
    this.authService.logout(); 
    this.isLoggedIn = false;    
    this.router.navigate(['/home']);  
  }

}
