import {Injectable} from '@angular/core';
import 'rxjs-compat/add/observable/of';


const TOKEN_KEY = 'AuthToken';

@Injectable()
export class TokenStorage {

  username: string;

  constructor() {
  }

  signOut() {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.clear();
  }

  public saveToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }


  public getDecodedToken(): any {

    let jwt = this.getToken();
    let jwtData = jwt.split('.')[1];
    let decodedJwtJsonData = window.atob(jwtData);
    let decodedJwtData = JSON.parse(decodedJwtJsonData);
    return decodedJwtData;
  }

  public getUsernameFromToken(): string {
    let jwt = this.getToken();
    let jwtData = jwt.split('.')[1];
    let decodedJwtJsonData = window.atob(jwtData);
    let decodedToken: { exp: number, iat: number, scopes: string[], sub: string };
    decodedToken = JSON.parse(decodedJwtJsonData);
    return decodedToken.sub;
  }
}
