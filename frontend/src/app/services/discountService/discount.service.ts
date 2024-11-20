import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Discount } from '../../model/discount.model';

@Injectable({
  providedIn: 'root'
})
export class DiscountService {

  private baseUrl = 'http://localhost:8080/v1/discounts';

  constructor(private http: HttpClient){ }

  getAllDiscounts(): Observable<Discount[]>{
    return this.http.get<Discount[]>(`${this.baseUrl}/`);
  }

  addDiscount(discount: Discount): Observable<Discount> {
    return this.http.post<Discount>(`${this.baseUrl}/`, discount);
  }

  getDiscountsByHotelId(hotelId: number): Observable<Discount[]> {
    return this.http.get<Discount[]>(`${this.baseUrl}/hotelDiscounts/${hotelId}`);
  }

  getDiscountById(id: number): Observable<Discount> {
    return this.http.get<Discount>(`${this.baseUrl}/${id}`);
  }

  updateDiscount(id: number, discount: Discount): Observable<Discount> {
    return this.http.patch<Discount>(`${this.baseUrl}/${id}`, discount);
  }

  deleteDiscount(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

  // Assign a contract to a discount
  assignContractToDiscount(discountId: number, contractId: number): Observable<Discount> {
    return this.http.put<Discount>(`${this.baseUrl}/${discountId}/contracts/${contractId}`, {});
  } 

}
