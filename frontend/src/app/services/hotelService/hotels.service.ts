import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Hotel } from '../../model/hotel.model';

@Injectable({
  providedIn: 'root'
})
export class HotelsService {

  private baseUrl = 'http://localhost:8080/v1/hotels';
  

  constructor(private http: HttpClient) {}

  // Get all hotels
  getHotels(): Observable<Hotel[]> {
    return this.http.get<Hotel[]>(`${this.baseUrl}/`);
  }

  //Add hotels
  addHotel(hotel: Hotel): Observable<Hotel>{
    return this.http.post<Hotel>(`${this.baseUrl}/`, hotel);
  }

  // Search hotels based on guest count, dates, and optional location
  searchHotels(guestCount: number, checkinDate: string, checkoutDate: string): Observable<Hotel[]> {
    let params = new HttpParams()
      .set('guestCount', guestCount.toString())
      .set('checkinDate', checkinDate)
      .set('checkoutDate', checkoutDate)

    console.log('hotelService/search : params: '+params)

    return this.http.get<Hotel[]>(`${this.baseUrl}/search`, { params });
  }

  // Get a single hotel by its ID
  getHotelById(hotelId: number): Observable<Hotel> {
    return this.http.get<Hotel>(`${this.baseUrl}/${hotelId}`);
  }

  //Edit hotel by Id
  editHotel(hotelId: number, hotel: Hotel): Observable<Hotel>{
    return this.http.patch<Hotel>(`${this.baseUrl}/${hotelId}`, hotel);
  }

  // Delete a hotel by its ID
  deleteHotel(hotelId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${hotelId}`);
  }

  
}
