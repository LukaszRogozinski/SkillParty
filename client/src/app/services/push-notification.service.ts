import {Injectable} from '@angular/core';
import {HttpService} from './http.service';

const NOTIFICATION_URL = 'http://localhost:3000/subscription';
const SEND_NOTIFICATION_URL = 'http://localhost:3000/sendNotification';
const SAVE_SUBSCRIPTION_TO_BACKEND_URL = 'http://localhost:8080/web-push';

@Injectable({
  providedIn: 'root'
})
export class PushNotificationService {

  constructor(private http: HttpService) {
  }

  public sendSubscriptionToTheServer(subscription: PushSubscription) {
    return this.http.post(NOTIFICATION_URL, subscription);
  }

  public sendNotification() {
    return this.http.post(SEND_NOTIFICATION_URL, null);
  }

  public saveSuscriptionToBackEnd(subscription: PushSubscription) {
    return this.http.post(SAVE_SUBSCRIPTION_TO_BACKEND_URL + '/subscribe', subscription);
  }
}
