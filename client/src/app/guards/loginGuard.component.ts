import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {TokenStorage} from '../core/token.storage';
import {IsLoggedService} from '../services/is-logged.service';

@Injectable()
export class LoginGuardComponent implements CanActivate {

  constructor(private isLoggedService: IsLoggedService,
              private tokenStorage: TokenStorage,
              private router: Router) {

  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (this.tokenStorage.getToken()) {
      this.router.navigate(['home']);
      return false;
    } else {
    //  this.tokenStorage.signOut();
      //this.isLoggedService.statusUpdated.next(false);
      return true;
    }
  }

}
