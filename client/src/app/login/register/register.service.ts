import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {NewUserModel} from './model/new-user.model';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private httpClient: HttpClient) { }

  private registerUrl = 'http://localhost:8080/register';

  public registerNewUser(newUser: NewUserModel): Observable<any> {
    return this.httpClient.post(this.registerUrl + '/new', newUser);
  }

}
