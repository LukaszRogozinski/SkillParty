import {Injectable} from '@angular/core';
import {HttpService} from './http.service';
import {Observable} from 'rxjs';
import {EmailMessage} from '../event/new-event/new-event.component';

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  private emailUrl = 'http://localhost:8080/email';

  constructor(private httpService: HttpService) {
  }

  public sendEmails(email: EmailMessage): Observable<any> {
    return this.httpService.post(this.emailUrl + '/send', email);
  }
}
