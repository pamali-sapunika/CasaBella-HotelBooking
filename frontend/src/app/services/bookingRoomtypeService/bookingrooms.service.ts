import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BookingRoomtypes } from '../../model/bookingrooms.model';

@Injectable({
  providedIn: 'root'
})
export class BookingroomsService {

  private baseUrl = 'http://localhost:8080/v1/bookingroomtypes';

  constructor(private http: HttpClient) { }

  getBookingRoomtypes(): Observable<BookingRoomtypes[]>{
    return this.http.get<BookingRoomtypes[]>(`${this.baseUrl}/`);
  }

  addRoomtypeToBooking(bookingId: number, seasonalRoomtypeId: number, bookingRoomtype: BookingRoomtypes): Observable<BookingRoomtypes> {
    return this.http.post<BookingRoomtypes>(`${this.baseUrl}/${bookingId}/seasonalRoomtype/${seasonalRoomtypeId}`, bookingRoomtype);
  }

}
