import {Component, OnInit} from '@angular/core';
import {User} from '../model/user.model';
import {UserService} from '../user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {MessageService} from '../../services/message.service';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {

  user: User;

  constructor(private userService: UserService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private messageService: MessageService) {
  }

  ngOnInit() {
    this.getUserDetail();
  }

  getUserDetail() {
    const username = this.activatedRoute.snapshot.paramMap.get('username');
    this.userService.getUserDetailByUsername(username).subscribe(
      response => this.user = response
    ),
      (error) => console.log(error);
  }

  editUser() {
    const username = this.activatedRoute.snapshot.paramMap.get('username');
    this.userService.saveUser(this.user, username).subscribe(
      response => {
        this.messageService.success('user details changed');
        console.log(response);
        this.router.navigate(['home']);
      },
      error => {
        console.log(error);
        this.messageService.error(error.toString());
      }
    );
  }

}
