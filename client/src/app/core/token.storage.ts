import { Injectable } from '@angular/core';
import {Observable, Subject} from 'rxjs';
import 'rxjs-compat/add/observable/of';


const TOKEN_KEY = 'AuthToken';

@Injectable()
export class TokenStorage {

  private userLoggedIn = new Subject();

  constructor() { }

  signOut() {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.clear();
  }

  public saveToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY,  token);
  }

  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public watchSession(): Observable<any> {
    if(this.getToken()){
      return Observable.of(true);
    } else {
      return Observable.of(false);
    }
    /*if(this.getToken()){
      this.userLoggedIn.next(true);
      return this.userLoggedIn;
    } else {
      return this.userLoggedIn;
    }*/
  }

  public getDecodedToken(): any {

    let jwt = this.getToken();
    let jwtData = jwt.split('.')[1];
    let decodedJwtJsonData = window.atob(jwtData);
    let decodedJwtData = JSON.parse(decodedJwtJsonData);
/*
    let isAdmin = decodedJwtData.admin

    console.log('jwtData: ' + jwtData)
    console.log('decodedJwtJsonData: ' + decodedJwtJsonData)
    console.log('decodedJwtData: ' + decodedJwtData)
    console.log('Is admin: ' + isAdmin)*/
    return decodedJwtData;
  }
}
