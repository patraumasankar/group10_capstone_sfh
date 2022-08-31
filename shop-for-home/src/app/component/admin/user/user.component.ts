import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  signUpForm !: FormGroup;
  hide = true;
  actionBtn: string = 'save';

  constructor(private userService: UserService,
    private _snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) public editData: any,
    private dialogRef: MatDialogRef<UserComponent>) { }

  ngOnInit(): void {
    this.initForm();

    if (this.editData) {
      this.actionBtn = 'update';
      this.signUpForm.controls['firstName'].setValue(this.editData.firstName);
      this.signUpForm.controls['lastName'].setValue(this.editData.lastName);
      this.signUpForm.controls['username'].setValue(this.editData.username);
      this.signUpForm.controls['email'].setValue(this.editData.email);
      this.signUpForm.controls['phone'].setValue(this.editData.phone);
      this.signUpForm.controls['password'].setValue('');
    }

  }

  private initForm() {
    this.signUpForm = new FormGroup({
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      phone: new FormControl('', Validators.required),
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
    });
  }

  addUser() {
    if (!this.editData) {
      if (this.signUpForm.valid) {
        this.userService.registerUser(this.signUpForm.value)
          .subscribe({
            next: (res) => {
              console.log(res);
              this._snackBar.open('User added successfully', 'close', {duration: 1000});
              this.signUpForm.reset();
              this.dialogRef.close('save');
            },
            error: () => {
              this._snackBar.open('Error while adding the User !!!', 'close', {duration: 1000});
            }
          })
      }
    } else {
      this.updateUser();
    }
  }

  updateUser() {
    if(this.signUpForm.valid) {
      this.userService.updateUser(this.signUpForm.value)
      .subscribe({
        next: (res) => {
          this._snackBar.open('User updated successfully', 'close', {duration: 1000});
          this.signUpForm.reset();
          this.dialogRef.close('update');
        },
        error: () => {
          this._snackBar.open('Error while updating the User !!!', 'close', {duration: 1000});
        }
      })
    }else{
      this._snackBar.open('Check all the fields', 'close', {duration: 1000});
    }
    
  }
  
}
