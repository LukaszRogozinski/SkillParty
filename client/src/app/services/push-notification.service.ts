import {Injectable} from '@angular/core';
import {HttpService} from './http.service';

const SEND_SPORT_NOTIFICATION_URL = 'http://localhost:3000/sendSportEvent';
const SEND_RELAX_NOTIFICATION_URL = 'http://localhost:3000/sendRelaxEvent';
const GET_NOTIFICATION_URL = 'http://localhost:3000/getAll';
const SAVE_SUBSCRIPTION_TO_BACKEND_URL = 'http://localhost:8080/web-push';

@Injectable({
  providedIn: 'root'
})
export class PushNotificationService {

  constructor(private http: HttpService) {
  }

  public getSubscriptionsFromDB() {
    return this.http.get(GET_NOTIFICATION_URL);
  }

  public sendSportNotification() {
    return this.http.post(SEND_SPORT_NOTIFICATION_URL, null);
  }

  public sendRelaxNotification() {
    return this.http.post(SEND_RELAX_NOTIFICATION_URL, null);
  }

  public saveSuscriptionToBackEnd(subscription: PushSubscription) {
    return this.http.post(SAVE_SUBSCRIPTION_TO_BACKEND_URL + '/subscribe', subscription);
  }
}
