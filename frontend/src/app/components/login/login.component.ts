import { Component, inject } from '@angular/core';
import {MatSelectModule} from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { RouterModule, Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../services/authService/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    MatIconModule, 
    MatButtonModule, 
    RouterModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    FormsModule

  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  username = '';
  password = '';
  returnUrl: string = ''; 

  constructor(private authService: AuthService, private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {
    // Capture the return URL from the query parameters
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  onSubmit() {
    console.log('login '+this.username, this.password);
    this.authService.login({ username: this.username, password: this.password }).subscribe({
      next: (response: string) => {
        console.log('JWT Token received: ', response); 
        this.authService.saveToken(response);
        this.router.navigateByUrl('home')  ;
        // this.router.navigateByUrl(this.returnUrl); 
      },
      error: () => {
        console.log('Error in login process');
        alert('Login failed');
      }
    });
  }

  logout(){
    this.authService.logout();
  }

}
