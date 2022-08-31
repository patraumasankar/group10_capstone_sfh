import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { UserService } from 'src/app/service/user.service';
import { UserComponent } from '../user/user.component';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  displayedColumns: string[] = ['id', 'firstname', 'lastname', 'username', 'email', 'phone', 'role', 'action'];
  dataSource !: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator !: MatPaginator;
  @ViewChild(MatSort) sort !: MatSort;
  
  constructor(private userService: UserService,
    public dialog: MatDialog,
    private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.getAllUser();
  }

  getAllUser() {
    this.userService.getAllUser().subscribe({
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

addUser() {
  this.dialog.open(UserComponent,{
    width:'35%'
  }).afterClosed().subscribe(value => {
    if (value === 'save'){
      this.getAllUser();
    }
  })
}

editUser(row:any) {
  this.dialog.open(UserComponent,{
    width:'35%',
    data:row,
  }).afterClosed().subscribe(value => {
    if (value === 'update'){
      this.getAllUser();
    }
  })
}

deleteUser(id:any){
  this.userService.deleteUser(id)
    .subscribe({
      next: (res) => {
        this._snackBar.open('Delete User Successfully', 'close', {duration: 1000});
        this.getAllUser();
      },
      error: () => {
        this._snackBar.open('Error while Deleting the User !!!', 'close', {duration: 1000});
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
