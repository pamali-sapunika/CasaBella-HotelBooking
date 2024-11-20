import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Passenger } from '../../model/passenger.model';

@Injectable({
  providedIn: 'root'
})
export class PassengerService {

  private baseUrl = 'http://localhost:8080/v1/passengers'

  constructor(private http: HttpClient) { }

  addPassenger(passenger: Passenger): Observable<Passenger>{
    return this.http.post<Passenger>(`${this.baseUrl}/`, passenger);
  }

  getPassengerById(passengerId: number): Observable<Passenger> {
    return this.http.get<Passenger>(`${this.baseUrl}/${passengerId}`)
  }

  updatePassenger(passengerId: number, passenger: Passenger): Observable<Passenger>{
    return this.http.put<Passenger>(`${this.baseUrl}/${passengerId}`, passenger);
  }

}
