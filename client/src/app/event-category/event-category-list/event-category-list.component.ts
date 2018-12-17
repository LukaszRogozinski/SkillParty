import {Component, OnInit} from '@angular/core';
import {EventCategory} from '../model/event-category.model';
import {EventCategoryService} from '../event-category.service';
import {Router} from '@angular/router';
import * as Stomp from 'stompjs';
import {FormBuilder, FormGroup} from '@angular/forms';
import {NotificationsService, NotificationType} from 'angular2-notifications';
import {MessageService} from '../../services/message.service';
import {SwPush} from '@angular/service-worker';
import {NewsletterService} from '../../services/newsletter.service';
import {PushNotificationService} from '../../services/push-notification.service';

@Component({
  selector: 'app-event-category-list',
  templateUrl: './event-category-list.component.html',
  styleUrls: ['./event-category-list.component.css']
})
export class EventCategoryListComponent implements OnInit {

  eventCategories: EventCategory[];

  readonly VAPID_PUBLIC_KEY = 'BI5mCqcxTLbVlaMXdtNgzuzvguutX4CW8SJ8qotChykJCs_9hBVlPfO-ccr4O6APBDCfe3DCICo8r-NVcR_tfrM';

  ws: any;
  name: string;
  disabled: boolean;
  pushSubscription: PushSubscription;

  constructor(private eventCategoryService: EventCategoryService,
              private routerLink: Router,
              private _notifications: NotificationsService,
              private _fb: FormBuilder,
              private messageService: MessageService,
              private swPush: SwPush,
              private newsletterService: NewsletterService,
              private pushService: PushNotificationService) {
  }

  ngOnInit() {
    this.eventCategoryService.getEventCategories().subscribe(
      response => {
        this.eventCategories = response;
      },
      (error) => console.log(error)
    );
  }


  subscribeToNotifications() {

    if (this.swPush.isEnabled) {
      this.swPush.requestSubscription({
        serverPublicKey: this.VAPID_PUBLIC_KEY
      })
        .then(subscription => {
          this.pushSubscription = subscription;
          console.log(JSON.stringify(this.pushSubscription));
         // this.pushService.sendSubscriptionToTheServer(subscription).subscribe();
          this.pushService.saveSuscriptionToBackEnd(this.pushSubscription).subscribe();
        })
        .catch(console.error);
    }


    /* this.swPush.requestSubscription({
       serverPublicKey: this.VAPID_PUBLIC_KEY
     })
       .then(sub =>
       {
         this.subscription = sub;
        // this.newsletterService.addPushSubscriber(this.subscription).subscribe();
         console.log('PushSubscription: ');
         console.log(this.subscription);
         console.log('Sub: ');
         console.log(sub);
       })
       .catch(err => console.error("Could not subscribe to notifications", err));*/
  }

  SendNotifications() {
    this.pushService.sendNotification().subscribe();
  }

  addToFavourite(eventCategory: EventCategory) {
    this.eventCategoryService.addToFavourite(eventCategory).subscribe(
      response => console.log('YEA added new category!' + response)
    );
    this.connect(eventCategory);
    this.routerLink.navigateByUrl('/home');
  }

  connect(eventCategoryName: EventCategory) {
    // connect to stomp where stomp endpoint is exposed
    // let ws = new SockJS(http://localhost:8080/greeting);
    const socket = new WebSocket('ws://localhost:8080/' + eventCategoryName.name.toLowerCase());
    this.ws = Stomp.over(socket);
    const that = this;
    this.ws.connect({}, function (frame) {
      that.ws.subscribe('/errors', function (message) {
        alert('Error ' + message.body);
      });
      that.ws.subscribe('/' + eventCategoryName.name.toLowerCase() + 'Topic/reply', function (message) {
        console.log(message);
        that.messageService.createNotification(message.body);
      });
      that.disabled = true;
    }, function (error) {
      alert('STOMP error ' + error);
    });
  }
}
