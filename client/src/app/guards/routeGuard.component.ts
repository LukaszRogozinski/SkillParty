import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {TokenStorage} from '../core/token.storage';
import {URLsAvability} from './URLsAvailabilityForUsersRoles';

@Injectable()
export class RouteGuardComponent implements CanActivate {

  decodedToken: { sub: string, scopes: string, iat: number, exp: number }
  rolesArray: String[];

  constructor(private tokenStorage: TokenStorage,
              private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    let avaliable = false;
    this.decodedToken = this.tokenStorage.getDecodedToken();
    this.rolesArray = this.decodedToken.scopes.split(',');
    for (const url in URLsAvability) {
      if (this.rolesArray.includes(url, 0)) {
        if (URLsAvability[url].includes(state.url)) {
          avaliable = true;
          break;
        }
      }else {
        this.router.navigateByUrl('pageNotAvaliable');
      }
    }
    return avaliable;
  }
}
