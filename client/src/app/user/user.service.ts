import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from './model/user.model';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  private userUrl = 'http://localhost:8080/user';

  public getUsers(): Observable<any> {
    return this.http.get(this.userUrl + '/all');
  }


  public getUserDetailByUsername(username: string): Observable<any> {
    return this.http.get(this.userUrl + '/detail/' + username);
  }

  public deleteSelectedUserByUsername(username: String): Observable<any> {
    return this.http.delete(this.userUrl + '/delete/' + username);
  }

  public saveUser(user: User, username: string): Observable<any> {
    return this.http.put(this.userUrl + '/update/' + username, user);
  }
}
