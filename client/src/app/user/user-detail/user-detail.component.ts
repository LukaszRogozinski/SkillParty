import { Component, OnInit } from '@angular/core';
import {TokenStorage} from '../../core/token.storage';
import {UserService} from '../user.service';
import {User} from '../model/user.model';
import {ActivatedRoute} from '@angular/router';
import {MessageService} from '../../services/message.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {

  user: User = new User();


  constructor(private userService: UserService,
              private token: TokenStorage,
              private route: ActivatedRoute,
              private messageService: MessageService) { }

  ngOnInit() {
    this.getUserDetail();
  }

  getUserDetail(){
    const username = this.route.snapshot.paramMap.get('username');
    this.userService.getUserDetailByUsername(username).subscribe(
      response => this.user = response
    ),
    (error) => {
      console.log(error);
      this.messageService.error(error.toString());
    }
  }

}
