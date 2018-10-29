import {Component, OnInit} from '@angular/core';
import {TokenStorage} from '../core/token.storage';
import { Router} from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit{

  roles = {ROLE_ADMIN: false, ROLE_USER: false};
  rolesArray = [];

  constructor(private token: TokenStorage,
              private router: Router) { }

  ngOnInit(): void {
    this.initRoles();
  }

  logout(): void {
    this.token.signOut();
    this.router.navigate(['login']);
  }

  private initRoles(){
    let decodedToken = this.token.getDecodedToken();
    this.rolesArray = decodedToken.scopes.split(',');
    for(let role in this.roles){
      Object.entries(this.roles).forEach(([key, value]) => this.roles[key] = this.hasRole(key))
    }
  }

  hasRole(role: string): boolean {
    for(var i=0; i < this.rolesArray.length; i++){
      if(this.rolesArray[i]===role){
        return true;
      } else {
        return false;
      }
    }
  }

}
