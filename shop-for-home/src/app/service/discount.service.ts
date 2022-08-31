import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DiscountService {

  baseUrl= 'http://localhost:8585/api/v1/coupon/';
  constructor(private http: HttpClient) { }

  createCoupon(coupon:any){
    return this.http.post<any>(this.baseUrl+'add',coupon);
  }

  getAllCoupon(){
    return this.http.get<any>(this.baseUrl+'getall');
  }

  getAllCouponByUser(username:any){
    return this.http.get<any>(this.baseUrl+'getall/'+username);
  }

  deleteCoupon(id:any){
    return this.http.delete<any>(this.baseUrl+'delete/'+id);
  }
}
