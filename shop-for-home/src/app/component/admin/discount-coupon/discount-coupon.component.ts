import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { DiscountService } from 'src/app/service/discount.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-discount-coupon',
  templateUrl: './discount-coupon.component.html',
  styleUrls: ['./discount-coupon.component.css']
})
export class DiscountCouponComponent implements OnInit {

  couponForm !: FormGroup;
  users: any = [];

  constructor(private discountService: DiscountService,
    private userService: UserService,
    private router: Router,
    private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.initForm();
    this.userService.getAllUser().subscribe(user => {
      this.users = user;
    })
  }

  private initForm() {
    this.couponForm = new FormGroup({
      couponName: new FormControl('', Validators.required),
      value: new FormControl('', Validators.required),
      username: new FormControl([], Validators.required),
    });
  }

  createCoupon() {
    if (this.couponForm.valid) {
      this.discountService.createCoupon(this.couponForm.value).subscribe(data => {
        console.log(data);
        this._snackBar.open('Coupon Successfully created', 'close', { duration: 1000 });
        this.router.navigate(['/coupon']);
      })
    }else{
      this._snackBar.open('check all fields','close',{duration:1000});
    }

  }

}
