import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Contract } from '../../model/contract.model';
import { Booking } from '../../model/booking.model';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  private baseUrl = 'http://localhost:8080/v1/bookings';

  constructor(private http: HttpClient) { }

  addBooking(booking: Booking): Observable<Booking> {
    return this.http.post<Booking>(`${this.baseUrl}/`, booking);
  }

  getAllBookings(): Observable<Booking[]> {
    return this.http.get<Booking[]>(`${this.baseUrl}/`);
  }

  //Get booking by booking ID
  getBookingById(id: number): Observable<Booking> {
    return this.http.get<Booking>(`${this.baseUrl}/${id}`);
  }

  //Get Bookings by userId
  getBookingsByUserId(userId: number): Observable<Booking[]>{
    return this.http.get<Booking[]>(`${this.baseUrl}/userBookings/${userId}`);
  }

  updateBooking(id: number, booking: Booking): Observable<Booking> {
    return this.http.patch<Booking>(`${this.baseUrl}/${id}`, booking);
  }

  //Assign contractId
  assignContractToBooking(bookingId: number, contractId: number): Observable<Booking> {
    return this.http.put<Booking>(`${this.baseUrl}/${bookingId}/contracts/${contractId}`, {});
  }

  //Assign discountId
  assignDiscountToBooking(bookingId: number, discountId: number): Observable<Booking> {
    return this.http.put<Booking>(`${this.baseUrl}/${bookingId}/discounts/${discountId}`, {});
  }

  //Assign passenger 
  assignPassengerToBooking(bookingId: number, passengerId: number): Observable<Booking> {
    return this.http.put<Booking>(`${this.baseUrl}/${bookingId}/passenger/${passengerId}`, {});
  }

  //Assign user 
  assignUserToBooking(bookingId: number, userId: number): Observable<Booking> {
    return this.http.put<Booking>(`${this.baseUrl}/${bookingId}/users/${userId}`, {});
  }



}
