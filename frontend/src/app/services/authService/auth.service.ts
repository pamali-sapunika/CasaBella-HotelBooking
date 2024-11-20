import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

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
    localStorage.setItem('currentUser', JSON.stringify(user));
  }

  // Get the stored user details
  getUser(): any {
    const user = localStorage.getItem('currentUser');
    return user ? JSON.parse(user) : null;
  }

  // Clear user data when logging out
  clearUser(): void {
    localStorage.removeItem('currentUser');
  }

   // After login, handle the return URL
  loginSuccessRedirect(): void {
    const returnUrl = this.router.routerState.snapshot.url || '/';
    this.router.navigateByUrl(returnUrl);
  }

  
}
