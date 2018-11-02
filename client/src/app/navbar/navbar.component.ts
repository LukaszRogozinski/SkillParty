import {Component, OnInit} from '@angular/core';
import {TokenStorage} from '../core/token.storage';
import { Router} from '@angular/router';
import {IsLoggedService} from '../services/is-logged.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit{

  roles = {ROLE_ADMIN: false, ROLE_USER: false};
  rolesArray = [];
  isLogged = false;

  constructor(private token: TokenStorage,
              private router: Router,
              private isLoggedService: IsLoggedService) { }

  ngOnInit(): void {
    this.isLoggedService.statusUpdated.subscribe(
      data => {
        this.isLogged = data;
        if(this.isLogged)
        {
          this.initRoles();
        } else {
          this.resetRoles();
        }
      }
    );
  }

  logout(): void {
    this.token.signOut();
    this.isLoggedService.statusUpdated.emit(false);
    this.router.navigate(['login']);
  }

  private initRoles(){
    let decodedToken = this.token.getDecodedToken();
    this.rolesArray = decodedToken.scopes.split(',');
      Object.entries(this.roles).forEach(([key, value]) => this.roles[key] = this.hasRole(key))

  }

  private resetRoles(){
    Object.entries(this.roles).forEach(([key, value]) => this.roles[key] = false);
  }

  hasRole(role: string): boolean {
    for(var i=0; i < this.rolesArray.length; i++){
      if(this.rolesArray[i]===role){
        return true;
      }
    }
    return false;
  }

}
