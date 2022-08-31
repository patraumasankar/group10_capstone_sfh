import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/service/user.service';
import { WishlistService } from 'src/app/service/wishlist.service';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit {

  wishlists:any = [];
  user:any;
  constructor(private wishlistService:WishlistService,
    private userService: UserService,
    private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.getWishlist();
  }

  getWishlist(){
   this.user = this.userService.getUser();
   this.wishlistService.getWishlist(this.user.id).subscribe(wishlist =>{
    this.wishlists = wishlist;
   });
  }

  deleteWishlist(id:any){
    this.wishlistService.deleteWishlist(id).subscribe(res=>{
      this._snackBar.open('product deleted successfully','close',{duration:1000});
      this.getWishlist();
    })
  }
}
