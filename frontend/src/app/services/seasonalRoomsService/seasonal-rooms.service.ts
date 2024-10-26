import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SeasonalRooms } from '../../model/seasonalRoom.model';

@Injectable({
  providedIn: 'root'
})
export class SeasonalRoomsService {

  private baseUrl = 'http://localhost:8080/v1/seasonalroomtypes';

  constructor(private http: HttpClient) { }

  addRoomtypeToSeason(
    roomtypeId: number, 
    seasonId: number, 
    roomtypeData: { 
      price: number, 
      noofRooms: number, 
      maxAdults: number, 
      noofReservedRooms: number }): Observable<SeasonalRooms> {
        
    return this.http.post<SeasonalRooms>(`${this.baseUrl}/${roomtypeId}/seasons/${seasonId}`, roomtypeData);
  }

  getSeasonalRoomtypes(): Observable<SeasonalRooms[]> {
    return this.http.get<SeasonalRooms[]>(`${this.baseUrl}/`);
  }

  getSeasonalRoomtypeById(seasonalRoomtypeId: number): Observable<SeasonalRooms> {
    return this.http.get<SeasonalRooms>(`${this.baseUrl}/${seasonalRoomtypeId}`);
  }

  // Assign Hotel to SeasonalRoomtype
  assignHotelToSeasonalRoomtype(seasonalRoomtypeId: number, hotelId: number): Observable<SeasonalRooms> {
    return this.http.put<SeasonalRooms>(`${this.baseUrl}/${seasonalRoomtypeId}/hotels/${hotelId}`, {});
  }

}
