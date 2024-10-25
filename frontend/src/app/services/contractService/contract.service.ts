import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Contract } from '../../model/contract.model';


@Injectable({
  providedIn: 'root'
})
export class ContractService {

  private baseUrl = 'http://localhost:8080/v1/contracts';

  constructor(private http: HttpClient) { }

  getContracts(): Observable<Contract[]>{
    return this.http.get<Contract[]>(`${this.baseUrl}/`);
  }

  //Get Contracts of hotel
  getContractsByHotelId(hotelId: number): Observable<Contract[]> {
    return this.http.get<Contract[]>(`${this.baseUrl}/hotel/${hotelId}`);
  }

  addContract(contract: Contract): Observable<Contract>{
    return this.http.post<Contract>(`${this.baseUrl}/`,  contract);
  }

  //Mapping
  assignHotelToContract(contractId: number, hotelId: number): Observable<void> {
    return this.http.put<void>(`${this.baseUrl}/${contractId}/hotels/${hotelId}`, {});
  }

  updateContract(contractId: number, contract: Contract): Observable<Contract> {
    return this.http.put<Contract>(`${this.baseUrl}/${contractId}`, contract);
  }

  deleteContract(contractId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${contractId}`);
  }
}
