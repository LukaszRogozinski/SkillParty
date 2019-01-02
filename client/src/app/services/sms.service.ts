import { Injectable } from '@angular/core';
import {HttpService} from './http.service';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SmsService {

  private SMSUrl = 'http://localhost:8080/SMS';

  constructor(private httpService: HttpService) {}

  public SendSMS(eventCategory: string): Observable<any> {
    return this.httpService.post(this.SMSUrl + '/send', eventCategory);
  }
}
