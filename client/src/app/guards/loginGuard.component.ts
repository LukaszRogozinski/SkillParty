import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {TokenStorage} from '../core/token.storage';

@Injectable()
export class LoginGuardComponent implements CanActivate {

  constructor(private tokenStorage: TokenStorage) {

  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (this.tokenStorage.getToken()) {
      return false;
    } else {
      return true;
    }
  }

}
