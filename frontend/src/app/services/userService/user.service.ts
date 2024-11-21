import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/v1/users';

  constructor(private http: HttpClient) { }

  register(user: User): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/register`, user);
  }

  //Username
  getUserByUsername(username: string): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/username/${username}`);
  }

  getUserById(userId: number): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/${userId}`);
  }

  updateUser(userId: number, user: User): Observable<User> {
    return this.http.patch<User>(`${this.baseUrl}/${userId}`, user)
  }

  deleteUser(userId: number): Observable<void>{
    return this.http.delete<void>(`${this.baseUrl}/${userId}`)
  }
}
