import { Component, OnInit } from '@angular/core';
import {TokenStorage} from '../core/token.storage';
import {Router} from '@angular/router';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private token: TokenStorage,
              private router: Router) { }


  ngOnInit() {

  }

  logout(): void {
    this.token.signOut();
    this.router.navigate(['login']);
  }


}
