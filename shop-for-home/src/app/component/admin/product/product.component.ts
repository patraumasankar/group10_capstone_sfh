import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ProductService } from 'src/app/service/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  productForm !: FormGroup;
  hide = true;
  actionBtn: string = 'save';

  constructor(private productService: ProductService,
    private _snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) public editData: any,
    private dialogRef: MatDialogRef<ProductComponent>
    ) { }

  ngOnInit(): void {
    this.initForm();

    if (this.editData) {
      this.actionBtn = 'update';
      this.productForm.controls['id'].setValue(this.editData.id);
      this.productForm.controls['productName'].setValue(this.editData.productName);
      this.productForm.controls['productDescription'].setValue(this.editData.productDescription);
      this.productForm.controls['productPrice'].setValue(this.editData.productPrice);
      this.productForm.controls['productStock'].setValue(this.editData.productStock);
      this.productForm.controls['url'].setValue(this.editData.url);
      this.productForm.controls['category'].setValue(this.editData.category);
    }

  }

  private initForm() {
    this.productForm = new FormGroup({
      id: new FormControl(),
      productName: new FormControl('', Validators.required),
      productDescription: new FormControl('', Validators.required),
      productStock: new FormControl('', [Validators.required]),
      productPrice: new FormControl('', Validators.required),
      url: new FormControl('', Validators.required),
      category: new FormControl('', Validators.required),
    });
  }

  addProduct() {
    if (!this.editData) {
      if (this.productForm.valid) {
        this.productService.addProduct(this.productForm.value)
          .subscribe({
            next: (res) => {
              console.log(res);
              this._snackBar.open('Product added successfully', 'close', {duration: 1000});
              this.productForm.reset();
              this.dialogRef.close('save');
            },
            error: () => {
              this._snackBar.open('Error while adding the Product !!!', 'close', {duration: 1000});
            }
          })
      }
    } else {
      this.updateProduct();
    }
  }

  updateProduct() {
    if(this.productForm.valid) {
      this.productService.updateProduct(this.productForm.value)
      .subscribe({
        next: (res) => {
          this._snackBar.open('Product updated successfully', 'close', {duration: 1000});
          this.productForm.reset();
          this.dialogRef.close('update');
        },
        error: () => {
          this._snackBar.open('Error while updating the Product !!!', 'close', {duration: 1000});
        }
      })
    }else{
      this._snackBar.open('Check all the fields', 'close', {duration: 1000});
    }
    
  }


}
