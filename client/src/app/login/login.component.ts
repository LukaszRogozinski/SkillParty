import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../core/auth.service';
import {TokenStorage} from '../core/token.storage';
import {IsLoggedService} from '../services/is-logged.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private router: Router,
              private authService: AuthService,
              private token: TokenStorage,
              private isLogedService: IsLoggedService
  ) {
  }

  username: string;
  password: string;

  login(): void {

    this.authService.attemptAuth(this.username, this.password).subscribe(
      data => {
        this.token.saveToken(data.token);
        this.router.navigate(['home']);
        this.isLogedService.statusUpdated.next(true);
      },
      error => console.log(error)
    );
  }

}
