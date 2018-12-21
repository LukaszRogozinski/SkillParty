import {Component, OnInit} from '@angular/core';
import {TokenStorage} from '../core/token.storage';
import { Router} from '@angular/router';
import {IsLoggedService} from '../services/is-logged.service';
import {AuthService} from '../core/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit{

  constructor(private token: TokenStorage,
              private router: Router,
              private isLoggedService: IsLoggedService,
              private tokenStorage: TokenStorage,
              public authService: AuthService) { }

  ngOnInit(): void {
    this.isLoggedService.statusUpdated.subscribe(
      (data: boolean) => {
        const isLogged = data;
        if(isLogged)
        {
          this.authService.username = this.tokenStorage.getUsernameFromToken();
        //  this.username = this.tokenStorage.getUsernameFromToken();
          this.authService.initRoles();
        } else {
          this.authService.resetRoles();
        }
      }
    );
  }

}
