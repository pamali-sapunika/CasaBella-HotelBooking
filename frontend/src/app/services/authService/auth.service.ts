import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/v1/users/login'; 

  constructor(private http: HttpClient) { }

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

  
}
