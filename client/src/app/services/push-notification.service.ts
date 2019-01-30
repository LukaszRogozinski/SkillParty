import {Injectable} from '@angular/core';
import {HttpService} from './http.service';

const SEND_NOTIFICATION_URL = 'http://localhost:3000/sendNotification';
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

  public sendNotification() {
    return this.http.post(SEND_NOTIFICATION_URL, null);
  }

  public saveSuscriptionToBackEnd(subscription: PushSubscription) {
    return this.http.post(SAVE_SUBSCRIPTION_TO_BACKEND_URL + '/subscribe', subscription);
  }
}
