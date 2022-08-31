import { Component, OnInit } from '@angular/core';
import { DiscountService } from 'src/app/service/discount.service';
import { CartService } from 'src/app/service/cart.service';
import { Order } from 'src/app/model/order';
import { UserService } from 'src/app/service/user.service';
import { OrderService } from 'src/app/service/order.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  order:Order={
    username: '',
    totalPrice: 0
  };

  carts: any = [];
  discountCoupons: any = [];
  user: any;
  quantity: any = 1;
  grandTotal: any = 0;
  isDisabled:boolean = false;

  constructor(private cartService: CartService,
    private discountService: DiscountService,
    private orderService: OrderService,
    private userService: UserService,
    private router: Router,
    private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.getCart();
    this.getCoupons();  
  }

  getCart() {
    this.user = this.userService.getUser();
    this.cartService.getCart(this.user.id).subscribe(carts => {
      this.carts = carts;
    });
    this.findTotalPrice();
  }

  getCoupons() {
    this.user = this.userService.getUser();
    this.discountService.getAllCouponByUser(this.user.username).subscribe(discount => {
      this.discountCoupons = discount;
    });
  }
  updateCart(cart: any, cartId: any) {
    this.cartService.updateCart(cart, cartId).subscribe(cart => {
      console.log(cart);
      this.getCart();
    });
  }

  deleteCart(id: any) {
    this.cartService.deleteCart(id).subscribe(res => {
      this.getCart();
      this._snackBar.open('Product deleted successfully','close',{duration:1000});
    })
  }

  findTotalPrice() {
    this.grandTotal = 0;
    this.cartService.getCart(this.user.id).subscribe(carts => {
      for(let cart of carts){
        if(cart.status==0)
        this.grandTotal += cart.totalPrice;
       }
    });
    
  }
  
  applyCoupon(coupon:any){
    this._snackBar.open('Coupon applied successfully','close',{duration:1000});
    this.grandTotal = this.grandTotal-((this.grandTotal*coupon)/100);
    this.isDisabled= true;
  }

  placeOrder(){
    this.order.username = this.userService.getUser().username;
    this.order.totalPrice = this.grandTotal;
    console.log(this.order);
    this.orderService.placeOrder(this.order).subscribe(data => {
      console.log(data);
      this._snackBar.open('Order Successful','close',{duration:1000});
      this.router.navigate(['/order']);
    });
  }
  
  decreaseQuantity(cart: any, id: any) {
    cart.quantity -= 1;
    cart.totalPrice = cart.quantity * cart.product.productPrice;
    this.updateCart(cart, id);   
  }
  increaseQuantity(cart: any, id: any) {
    cart.quantity += 1;
    cart.totalPrice = cart.quantity * cart.product.productPrice;
    this.updateCart(cart, id);   
  }
}
