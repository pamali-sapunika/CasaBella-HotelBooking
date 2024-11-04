import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BookingSupplements } from '../../model/bookingsupplement.model';

@Injectable({
  providedIn: 'root'
})
export class BookingsupplementsService {

  private baseUrl = 'http://localhost:8080/v1/bookingsupplements';

  constructor(private http: HttpClient) { }

  getBookingSupplements(): Observable<BookingSupplements[]> {
    return this.http.get<BookingSupplements[]>(`${this.baseUrl}/`);
  }

  addSupplementToBooking(bookingId: number, seasonalSupplementId: number, bookingSupplement: BookingSupplements): Observable<BookingSupplements> {
    return this.http.post<BookingSupplements>(`${this.baseUrl}/${bookingId}/seasonalSupplement/${seasonalSupplementId}`, bookingSupplement);
  }

}
