import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  loginForm !: FormGroup;
  hide=true;

  constructor(private userService: UserService,
    private router: Router,
    private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.initForm();
  }

  private initForm(){
    this.loginForm = new FormGroup({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required])
    });
  }

  login(){
    if(this.loginForm.valid){
      this.userService.authenticateUser(this.loginForm.value).subscribe(res => {
        console.log(res);
        this.userService.loginUser(res.token, res.user, res.user.role);
        this.userService.loggedIn.next(true);
        this.userService.userRole.next(res.user.role);
        this._snackBar.open('Login Successful','close',{duration:1000});
        this.router.navigate(['/home/1']);
      });
    }  
  }

}
