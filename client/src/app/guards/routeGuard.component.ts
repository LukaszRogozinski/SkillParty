import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {TokenStorage} from '../core/token.storage';
import {URLsAvability} from './URLsAvailabilityForUsersRoles';
@Injectable()
export class RouteGuardComponent implements CanActivate {

  decodedToken: {sub: string, scopes: string, iat: number, exp: number}
  rolesArray: String[];

  constructor(private tokenStorage: TokenStorage) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    let avaliable = false;
    this.decodedToken = this.tokenStorage.getDecodedToken();
    this.rolesArray = this.decodedToken.scopes.split(',');
    this.rolesArray[0] = this.rolesArray[0].substring(5,this.rolesArray[0].length);
    this.rolesArray[1] = this.rolesArray[1].substring(5,this.rolesArray[1].length);

    for(const role of this.rolesArray)
      for(const url in URLsAvability){
        let a =  this.rolesArray.includes(url,0)
        if(this.rolesArray.includes(url,0)){
          avaliable = true;
          break;
        }
      }

    return avaliable;
  }
}
