import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {

  baseUrl = 'http://localhost:8585/api/v1/wishlist/';
  constructor(private http: HttpClient) { }

  getWishlist(userId:any){
    return this.http.get<any>(this.baseUrl+'getall/'+userId)
  }

  addWishlist(productId:any){
    return this.http.get<any>(this.baseUrl+'add/'+productId);
  }

  deleteWishlist(wishlistId:any){
    return this.http.delete<any>(this.baseUrl+'delete/'+wishlistId);
  }

}
