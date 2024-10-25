import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Supplement } from '../../model/supplement.model';

@Injectable({
  providedIn: 'root'
})
export class SupplementService {

  private baseUrl = 'http://localhost:8080/v1/supplements';

  constructor(private http: HttpClient) { }

  getSupplements(): Observable<Supplement[]> {
    return this.http.get<Supplement[]>(`${this.baseUrl}/`);
  }

  getSupplementById(id: number): Observable<Supplement> {
    return this.http.get<Supplement>(`${this.baseUrl}/${id}`);
  }

  addSupplement(supplement: Supplement): Observable<Supplement> {
    return this.http.post<Supplement>(`${this.baseUrl}/`, supplement);
  }

  updateSupplement(id: number, supplement: Supplement): Observable<Supplement> {
    return this.http.patch<Supplement>(`${this.baseUrl}/${id}`, supplement);
  }

  deleteSupplement(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }


}
