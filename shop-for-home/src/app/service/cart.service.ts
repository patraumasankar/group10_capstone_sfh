import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  baseUrl = 'http://localhost:8585/api/v1/cart/';
  constructor(private http: HttpClient) { }

  getCart(userId:any){
    return this.http.get<any>(this.baseUrl+'getall/'+userId);
  }

  addCart(productId:any){
    return this.http.get<any>(this.baseUrl+'add/'+productId);
  }

  updateCart(cart:any, cartId:any){
    return this.http.put<any>(this.baseUrl+'update/'+cartId,cart);
  }

  deleteCart(cartId:any){
    return this.http.delete<any>(this.baseUrl+'delete/'+cartId);
  }
}
