import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { UserService } from '../service/user.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
  constructor(private userService: UserService,
    private router: Router){}
    
  canActivate(){    
      
    if(this.userService.getRole() == "ROLE_USER"){
        return true;
      }
      this.router.navigate(['/dashboard']);
      return false;
  }
  
}
