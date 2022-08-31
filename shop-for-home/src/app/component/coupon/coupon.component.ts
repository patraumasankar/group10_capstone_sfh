import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DiscountService } from 'src/app/service/discount.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-coupon',
  templateUrl: './coupon.component.html',
  styleUrls: ['./coupon.component.css']
})
export class CouponComponent implements OnInit {

  discountCoupons:any = [];
  role:any;
  user:any;

  constructor(private discountService: DiscountService,
    private userService: UserService,
    private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.role=this.userService.getRole();
    this.user=this.userService.getUser();
    this.getcoupons();
  }

  getcoupons(){
    if(this.role == 'ROLE_ADMIN'){
      this.discountService.getAllCoupon().subscribe(discount =>{
        this.discountCoupons = discount;
      });
    }else{
      this.discountService.getAllCouponByUser(this.user.username).subscribe(discount =>{
        this.discountCoupons = discount;
      });
    }  
  }

  deleteCoupon(id:any){
    this.discountService.deleteCoupon(id).subscribe(res =>{
      this.getcoupons();
      this._snackBar.open('Delete coupon Successful','close',{duration:1000});
    });
  }
}
