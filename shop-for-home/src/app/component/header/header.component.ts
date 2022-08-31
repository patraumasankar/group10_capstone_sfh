import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/service/product.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public isloggedIn!: boolean;
  public user: any = {};
  role: any;
  constructor(private userService: UserService,
    private router: Router) { }

  ngOnInit(): void {
    this.userService.loggedIn.subscribe(login => {
      this.isloggedIn = login;
      if (this.isloggedIn) {

        this.user = this.userService.getUser();
        console.log(this.user);

        this.userService.userRole.subscribe(role => {
          this.role = role;          
        });
      }

    },err=>{
      this.logout();
      this.router.navigate(['/home/1']);
    })
  }

  logout() {
    this.userService.logout();
    this.userService.loggedIn.next(false);
  }
}
