import { Component, OnInit } from '@angular/core';
import {NewUserModel} from './model/new-user.model';
import {RegisterService} from './register.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser: NewUserModel = new NewUserModel();

  constructor(private registerService: RegisterService,
              private router: Router) { }

  ngOnInit() {
  }

  registerNewUser() {
    this.registerService.registerNewUser(this.newUser).subscribe(
      response => console.log('User registered' + response),
      error => console.log(error)
    );
    this.router.navigateByUrl('/login');
  }

}
