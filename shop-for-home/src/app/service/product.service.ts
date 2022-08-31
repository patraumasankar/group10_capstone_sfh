import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  
  baseUrl = 'http://localhost:8585/api/v1/product/';
  constructor(private http: HttpClient) { }

  getProduct(){
    return this.http.get<any>(this.baseUrl+'getall');
  }

  getProductPage(page:any){
    return this.http.get<any>(this.baseUrl+'getall/'+page);
  }

  getProductByCategory(category:any){
    return this.http.get<any>(this.baseUrl+'getall/category/'+category);
  }

  getProductSortedAsc(field:any,page:any){
    return this.http.get<any>(this.baseUrl+'getall/sort/asc/'+field+'/'+page);
  }

  getProductSortedDesc(field:any,page:any){
    return this.http.get<any>(this.baseUrl+'getall/sort/desc/'+field+'/'+page);
  }

  addProduct(product:any){
    return this.http.post<any>(this.baseUrl+'add', product);
  }

  updateProduct(product:any){
    return this.http.put<any>(this.baseUrl+'update', product);
  }

  deleteProduct(id:any){
    return this.http.delete<any>(this.baseUrl+'delete/'+id);
  }
}
