import { Component, OnInit } from '@angular/core';
import {TokenStorage} from '../../core/token.storage';
import {UserService} from '../user.service';
import {User} from '../model/user.model';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {

  user: User = new User();

  constructor(private userService: UserService, private token: TokenStorage) { }

  ngOnInit() {
    this.userService.getLoggedUserDetail().subscribe(
      response => {
             this.user = response;
           },
      (error) => console.log(error)
      );
  }


}
