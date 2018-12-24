import {Component, OnInit} from '@angular/core';
import {NewUserModel} from './model/new-user.model';
import {RegisterService} from './register.service';
import {Router} from '@angular/router';
import {MessageService} from '../../services/message.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser: NewUserModel = new NewUserModel();

  constructor(private registerService: RegisterService,
              private router: Router,
              private messageService: MessageService) {
  }

  ngOnInit() {
  }

  registerNewUser() {
    this.registerService.registerNewUser(this.newUser).subscribe(
      () => this.messageService.success("Register complete!"),
      error => console.log(error)
    );
    this.router.navigateByUrl('/login');
  }

}
