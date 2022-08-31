import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  file: any;
  userview: boolean = true;
  productview: boolean = false;
  csvview: boolean = false;
  couponview: boolean = false;

  constructor(private http: HttpClient,
    private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
  }

  selectFile(event: any) {
    console.log(event)
    this.file = event.target.files[0]
    console.log(this.file)
  }

  uploadFile() {
    let formData = new FormData()
    formData.append("file", this.file)
    this.http.post('http://localhost:8585/api/csv/upload', formData).subscribe(res => {
      this._snackBar.open('file uploaded successfully','close',{duration:1000});
    }, (err) => {
      this._snackBar.open('file upload Unsuccessful','close',{duration:1000});
    })
  }

  // downloadReport() {
  //   let url = "http://localhost:8181/api/csv/download/prd.csv";
  //   this.http.get<any>(url).subscribe(res => {
  //     this._snackBar.open('download Report successfully','close',{duration:1000});
  //   });
  // }

  toggleUser() {
    this.userview = !this.userview;
    this.productview = false;
    this.csvview = false;
    this.couponview = false;
  }
  toggleProduct() {
    this.userview = false;
    this.productview = !this.productview;
    this.csvview = false;
    this.couponview = false;
  }
  toggleCSV() {
    this.userview = false;
    this.productview = false;
    this.csvview = !this.csvview;
    this.couponview = false;
  }
  toggleCoupon() {
    this.userview = false;
    this.productview = false;
    this.csvview = false;
    this.couponview = !this.couponview;
  }
}
