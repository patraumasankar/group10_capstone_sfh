import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  baseUrl = 'http://localhost:8585';
  constructor(private http: HttpClient) { }

  loggedIn = new BehaviorSubject<boolean>(this.isLoggedIn());
  userRole = new BehaviorSubject<any>(this.getRole());

  registerUser(user: any) {
    return this.http.post<any>(this.baseUrl+"/register", user);
  }

  authenticateUser(user: any) {
    return this.http.post<any>(this.baseUrl+"/authenticate", user);
  }

  getAllUser() {
    return this.http.get<any>(this.baseUrl+"/api/v1/user/getall");
  }

  getUserByUsername(username:any){
    return this.http.get<any>(this.baseUrl+"/api/v1/user/get/"+username);
  }

  updateUser(user:any){
    return this.http.put<any>(this.baseUrl+"/api/v1/user/update", user);
  }

  deleteUser(id:any){
    return this.http.delete<any>(this.baseUrl+"/api/v1/user/delete/"+id);
  }

  getToken() {
    return window.sessionStorage.getItem("token");
  }

  public getUser(): any {
    const user = window.sessionStorage.getItem("user");
    if (user) {
      return JSON.parse(user);
    }

    return {};
  }

  getRole() {
    return window.sessionStorage.getItem("role");
  }

  loginUser(token: string, user:any, role: string) {
    window.sessionStorage.setItem("token", token);
    window.sessionStorage.setItem("user",  JSON.stringify(user));
    window.sessionStorage.setItem("role", role);

    return true;
  }

  isLoggedIn():boolean {
    let token = window.sessionStorage.getItem("token");
    if (token == undefined || token === '' || token == null) {
      return false;
    } else {
      return true;
    }
  }

  logout() {
    window.sessionStorage.clear();
    return true;
  }

}
