import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Season } from '../../model/season.model';

@Injectable({
  providedIn: 'root'
})
export class SeasonService {

  private baseUrl = 'http://localhost:8080/v1/seasons';

  constructor(private http: HttpClient) { }


  getSeasons(): Observable<Season[]> {
    return this.http.get<Season[]>(`${this.baseUrl}/`);
  }

  getSeasonById(id: number): Observable<Season> {
    return this.http.get<Season>(`${this.baseUrl}/${id}`);
  }

  addSeason(season: Season): Observable<Season> {
    return this.http.post<Season>(`${this.baseUrl}/`, season);
  }

  // Get seasons by Contract ID
  getSeasonsByContractId(contractId: number): Observable<Season[]> {
    return this.http.get<Season[]>(`${this.baseUrl}/contract/${contractId}`);
  }

  updateSeason(id: number, season: Season): Observable<Season> {
    return this.http.patch<Season>(`${this.baseUrl}/${id}`, season);
  }


  deleteSeason(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  // Assign a contract to a season
  assignContractToSeason(seasonId: number, contractId: number): Observable<Season> {
    return this.http.put<Season>(`${this.baseUrl}/${seasonId}/contracts/${contractId}`, {});
  }
}
