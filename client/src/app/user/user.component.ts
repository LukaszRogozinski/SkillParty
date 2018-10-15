import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from './user.service';
import {User} from './model/user.model';
import {TokenStorage} from '../core/token.storage';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  displayedColumns = ['username', 'name', 'surname', 'email', 'city', 'street', 'houseNo', 'flatNo', 'averageVote'];
  users: User[];
  constructor(private router: Router, private userService: UserService, private token: TokenStorage) {
  }
  ngOnInit(): void {
    this.userService.getUsers().subscribe(
      data => {
        this.users = data;
      }
    );
  }

  logout(): void {
    this.token.signOut();
    this.router.navigate(['login']);
  }

  event(): void {
    this.router.navigate(['event']);
  }

}
