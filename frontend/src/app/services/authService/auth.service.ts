import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { jwtDecode } from "jwt-decode";




@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/v1/users/login'; 

  constructor(private http: HttpClient, private router: Router) { }

  login(credentials: { username: string, password: string }): Observable<any> {
    console.log('Login Login', credentials.username, credentials.password)
    return this.http.post(this.apiUrl, credentials, { responseType: 'text' });
  }

  saveToken(token: string): void{
    localStorage.setItem('jwtToken', token);
  }

  getToken(): string | null {
    return localStorage.getItem('jwtToken');
  }

  isAuthenticated(): boolean{
    return !!this.getToken();
  }

  logout(): void {
    localStorage.removeItem('jwtToken');
    console.log('jwt Token removed');
  }

  setUser(user: any): void {
    console.log('Setting user in localStorage:', user); // Debugging line
    localStorage.setItem('currentUser', JSON.stringify(user));
  }

  // Get the stored user details
  getUser(): any {
    const user = localStorage.getItem('currentUser');
    return user ? JSON.parse(user) : null;
  }

  getUserDetails(): Observable<any> {
    return this.http.get(`${this.apiUrl}/details`, {
      headers: { 'Authorization': `Bearer ${this.getToken()}` }
    });
  }

  // Clear user data when logging out
  clearUser(): void {
    localStorage.removeItem('currentUser');
  }

  // After login return to the same url
  loginSuccessRedirect(): void {
    //Saved return URL from localStorage
    const returnUrl = localStorage.getItem('returnUrl') || '/home'; 
    localStorage.removeItem('returnUrl'); 
    this.router.navigateByUrl(returnUrl);
  }

  getUserIdFromToken(): string | null {
    const token = this.getToken();
    if (token) {
      try {
        const decoded: any = jwtDecode(token);  
        console.log('decoded token', decoded);
        return decoded.sub || null;  
      } catch (error) {
        console.error('Failed to decode JWT:', error);
        return null;
      }
    }
    return null;
  }
  



  
}
