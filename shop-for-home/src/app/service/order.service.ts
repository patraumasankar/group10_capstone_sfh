import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Order } from '../model/order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  baseUrl = 'http://localhost:8585/api/v1/order/';

  constructor(private http:HttpClient) { }

  placeOrder(order:Order){
    return this.http.post<any>(this.baseUrl+'place-order',order);
  }

  getAllOrders(){
    return this.http.get<any>(this.baseUrl+'getall');
  }

}
