import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ProductService } from 'src/app/service/product.service';
import { ProductComponent } from '../product/product.component';


@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  displayedColumns: string[] = ['id', 'url', 'productName', 'productPrice', 'productStock', 'category', 'action'];
  dataSource !: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator !: MatPaginator;
  @ViewChild(MatSort) sort !: MatSort;
  
  constructor(private productService: ProductService,
    public dialog: MatDialog,
    private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.getAllProduct();
  }

  getAllProduct() {
    this.productService.getProduct().subscribe({
      next:(users)=> {
        this.dataSource = new MatTableDataSource(users);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
      error:(err)=> {
        this._snackBar.open('Error while fetching the data', 'close', {duration: 2000});
      }
    })
    
}

addProduct() {
  this.dialog.open(ProductComponent,{
    width:'35%'
  }).afterClosed().subscribe(value => {
    if (value === 'save'){
      this.getAllProduct();
    }
  })
}

editProduct(row:any) {
  this.dialog.open(ProductComponent,{
    width:'35%',
    data:row
  }).afterClosed().subscribe(value => {
    if (value === 'update'){
      this.getAllProduct();
    }
  })
}

deleteProduct(id:any){
  this.productService.deleteProduct(id)
    .subscribe({
      next: (res) => {
        this._snackBar.open('Delete Product Successfully', 'close', {duration: 1000});
        this.getAllProduct();
      },
      error: () => {
        this._snackBar.open('Error while Deleting the Product !!!', 'close', {duration: 1000});
      }
    })
}

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

}
