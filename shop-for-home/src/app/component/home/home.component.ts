import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { CartService } from 'src/app/service/cart.service';
import { ProductService } from 'src/app/service/product.service';
import { UserService } from 'src/app/service/user.service';
import { WishlistService } from 'src/app/service/wishlist.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  products: any = []
  user:any;

  wishlistProducts:any = [];
  public currentPage: any;
  public totalpage: any;
  public pageCounter: any = [];
  prvsBtnDisabled!: boolean;
  nextBtnDisabled!: boolean;
  showPagination: boolean = true;
  filter:any = 'ALL';
  field:any ;
  constructor(private productService: ProductService,
    private userService: UserService,
    private wishlistService: WishlistService,
    private cartService: CartService,
    private router: Router,
    private route: ActivatedRoute,
    private _snackBar: MatSnackBar) { }

  ngOnInit(): void {

    this.productService.getProductPage(1).subscribe(data => {
      this.totalpage = data.totalPages;
      console.log(data.totalPages);
      for (let i = 1; i <= this.totalpage; i++) {
        this.pageCounter.push(i);
      }
    })

    this.getAllProduct();
    if(this.userService.isLoggedIn()){
      this.getWishlist();
    }
    
  }

  getAllProduct() {
    this.route.paramMap.subscribe(res => {
      var page_num = res.get("page");
      this.currentPage = res.get("page");
      this.disableBtn();
      this.showPagination = true;
      if(this.filter == 'ALL'){
        this.productService.getProductPage(page_num).subscribe(data => {
          this.products = data.content;
        })
      }else if(this.filter == 'ASC'){
        this.productService.getProductSortedAsc(this.field,this.currentPage).subscribe(data => {
          this.products = data.content;
        })
      }else{
        this.productService.getProductSortedDesc(this.field,this.currentPage).subscribe(data => {
          this.products = data.content;
        })
      }
     
    })
  }

  allProduct(){
    this.disableBtn();
    this.showPagination = true;
    this.filter = 'ALL';
    this.productService.getProductPage(1).subscribe(data => {
      this.products = data.content;
    })
  }

  filterCategory(category: any) {
    this.productService.getProductByCategory(category).subscribe(data => {
      this.products = data;
      this.showPagination= false;
    })
  }

  getProductSortedAsc(field: any) {
    this.field = field;
    this.filter = 'ASC';
    this.productService.getProductSortedAsc(field,1).subscribe(data => {
      this.products = data.content;
    })
  }

  getProductSortedDesc(field: any) {
    this.field = field;
    this.filter = 'DESC';
    this.productService.getProductSortedDesc(field,1).subscribe(data => {
      this.products = data.content;
    })
  }

  addWishlist(id: any) {
    if(this.userService.isLoggedIn()){
      this.wishlistService.addWishlist(id).subscribe(data => {
        console.log(data);
        this.getWishlist();
      })
    }else{
      this._snackBar.open('please login to add product','close',{duration:1000});
      this.router.navigate(['/login']);
    }
    
  }

  getWishlist(){
    this.user = this.userService.getUser();
    this.wishlistService.getWishlist(this.user.id).subscribe(wishlists =>{
      for(let wishlist of wishlists){
        this.wishlistProducts.push(wishlist.product.id);
      }
    });
   }

  addCart(id: any) {
    if(this.userService.isLoggedIn()){
      this.cartService.addCart(id).subscribe(data => {
        console.log(data);
        this._snackBar.open('product added Successfully','close',{duration:1000});
      })
    }else{
      this._snackBar.open('please login to add product','close',{duration:1000});
      this.router.navigate(['/login']);
    }   
  }


  disableBtn() {
    if (this.currentPage == 1) {
      this.prvsBtnDisabled = true;
    } else {
      this.prvsBtnDisabled = false;
    }
    if (this.currentPage == this.totalpage) {
      this.nextBtnDisabled = true;
    } else {
      this.nextBtnDisabled = false;
    }
  }
}
