import { Component, OnInit } from '@angular/core';
import {TokenStorage} from '../core/token.storage';
import {Router} from '@angular/router';
import {URLsAvability} from '../guards/URLsAvailabilityForUsersRoles';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  decodedToken: {sub: string, scopes: string, iat: number, exp: number}
  rolesArray: String[];

  constructor(private token: TokenStorage,
              private router: Router) { }


  ngOnInit() {

  }

  logout(): void {
    this.token.signOut();
    this.router.navigate(['login']);
  }

  decode(): void {
  this.decodedToken = this.token.getDecodedToken();
  this.rolesArray = this.decodedToken.scopes.split(',');
  this.rolesArray[0] = this.rolesArray[0].substring(5,this.rolesArray[0].length);
  this.rolesArray[1] = this.rolesArray[1].substring(5,this.rolesArray[1].length);
  for(const url in URLsAvability){
    let a =  this.rolesArray.includes(url,0)
    break;
    let z = 5;
  }

    let tokenutu =  this.token.getDecodedToken();

  }

}
