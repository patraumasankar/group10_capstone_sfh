import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/service/cart.service';
import { OrderService } from 'src/app/service/order.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  orders: any = [];
  user_order:any = [];
  user:any ;
  role:any;
  constructor(private cartService: CartService,
    private userService: UserService,
    private orderService: OrderService) { }

  ngOnInit(): void {
    this.role = this.userService.getRole();
    this.getCart();
    this.getOrder();
  }

  getCart() {
    this.user = this.userService.getUser();
    this.cartService.getCart(this.user.id).subscribe(order => {
      this.orders = order;
    });
  }

  getOrder(){
    this.orderService.getAllOrders().subscribe(orders=>{
      this.user_order = orders;
      console.log(orders);
    })
  }

  generateReport(){
    window.print();
  }
}
