import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  private userUrl = 'http://localhost:8080/user';

  public getUsers(): Observable<any> {
    return this.http.get(this.userUrl + '/all');
  }

  public getLoggedUserDetail(): Observable<any> {
   return this.http.get(this.userUrl + '/detail');
  }
}
