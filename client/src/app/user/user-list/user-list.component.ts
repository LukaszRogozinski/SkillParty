import { Component, OnInit } from '@angular/core';
import {User} from '../model/user.model';
import {UserService} from '../user.service';
import {Router} from '@angular/router';
import {MessageService} from '../../services/message.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: User[];

  constructor(private router: Router, private userService: UserService,
              private messageService: MessageService) { }

  ngOnInit(): void {
    this.userService.getUsers().subscribe(
      response => {
        this.users = response;
      },
      (error) => console.log(error)
    );
  }

  deleteUser(user: User){
    this.messageService.confirm(
      'Delete User', 'Do you want delete this user?', 'Yes', 'No'
    ).subscribe(
      confirmed => {
        if(confirmed) {
          this.userService.deleteSelectedUserByUsername(user.username).subscribe(
            () => {
              this.router.navigate(["home"]);
              this.messageService.success("User successfully deleted.");
            },
            error => console.log('error' + error)
          );
        }
      }
    );

  }

}
