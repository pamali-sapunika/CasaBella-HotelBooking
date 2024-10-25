import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Roomtype } from '../../model/room.model';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private baseUrl = 'http://localhost:8080/v1/roomtypes';

  constructor(private http: HttpClient) { }


  getRoomtypes(): Observable<Roomtype[]> {
    return this.http.get<Roomtype[]>(`${this.baseUrl}/`);
  }

  getRoomtypeById(id: number): Observable<Roomtype> {
    return this.http.get<Roomtype>(`${this.baseUrl}/${id}`);
  }

  addRoomtype(roomtype: Roomtype): Observable<Roomtype> {
    return this.http.post<Roomtype>(`${this.baseUrl}/`, roomtype);
  }

  updateRoomtype(id: number, roomtype: Roomtype): Observable<Roomtype> {
    return this.http.patch<Roomtype>(`${this.baseUrl}/${id}`, roomtype);
  }

  deleteRoomtype(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

}
