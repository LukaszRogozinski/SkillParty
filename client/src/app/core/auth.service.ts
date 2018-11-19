import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {HttpService} from '../services/http.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  baseUrl: 'http://localhost:8080/email2sms/';

  constructor(private httpService: HttpService) {
  }

  attemptAuth(ussername: string, password: string): Observable<any> {
    const credentials = {username: ussername, password: password};
    console.log('attempAuth ::');
    return this.httpService.post<any>('http://localhost:8080/token/generate-token', credentials);
  }
}
