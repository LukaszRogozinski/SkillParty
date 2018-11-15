import { Component, OnInit } from '@angular/core';
import {User} from '../model/user.model';
import {UserService} from '../user.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {

  user: User;

  constructor(private userService: UserService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.getUserDetail();
  }

  getUserDetail(){
    const username = this.route.snapshot.paramMap.get('username');
    this.userService.getUserDetailByUsername(username).subscribe(
      response => this.user = response
    ),
      (error) => console.log(error);
  }

  editUser(){
    const username = this.route.snapshot.paramMap.get('username');
    this.userService.saveUser(this.user, username).subscribe(
      response => console.log(response),
      error => console.log(error)
    );
  }

}
