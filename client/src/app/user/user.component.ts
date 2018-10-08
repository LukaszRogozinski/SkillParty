import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {Router} from '@angular/router';
import {UserService} from './user.service';
import {User} from './user.model';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  displayedColumns = ['id', 'username'];
  users: User[];
  dataSource = new MatTableDataSource();
  constructor(private router: Router, private userService: UserService) {
  }
  ngOnInit(): void {
    this.userService.getUsers().subscribe(
      data => {
        this.dataSource.data = data;
      }
    );
  }

}
