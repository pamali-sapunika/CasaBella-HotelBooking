import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SeasonalSupplement } from '../../model/seasonalSupplement.model';
import { AvaialbilitySupplements } from '../../model/availabilitySupple.dto';

@Injectable({
  providedIn: 'root'
})
export class SeasonalSupplementService {

  private baseUrl = 'http://localhost:8080/v1/seasonalsupplements';

  constructor(private http: HttpClient) { }

  addSupplementToSeason(supplementId: number, seasonId: number, 
    supplementData: { 
      pricePerUnit: number 
    }): Observable<SeasonalSupplement> {
    return this.http.post<SeasonalSupplement>(`${this.baseUrl}/${supplementId}/seasons/${seasonId}`, supplementData);
  }

  getSeasonalSupplementsWithNames(seasonId: number): Observable<AvaialbilitySupplements[]> {
    return this.http.get<AvaialbilitySupplements[]>(`${this.baseUrl}/setSupplementName/${seasonId}`);
  }

  getSeasonalSupplements(): Observable<SeasonalSupplement[]> {
    return this.http.get<SeasonalSupplement[]>(`${this.baseUrl}`);
  }

  getSeasonalSupplementById(seasonalSupplementId: number): Observable<SeasonalSupplement> {
    return this.http.get<SeasonalSupplement>(`${this.baseUrl}/${seasonalSupplementId}`);
  }
}
