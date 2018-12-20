import {Component, OnInit} from '@angular/core';
import {EventCategory} from '../model/event-category.model';
import {EventCategoryService} from '../event-category.service';
import {Router} from '@angular/router';
import {FormBuilder} from '@angular/forms';
import {NotificationsService} from 'angular2-notifications';
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
          this.pushService.saveSuscriptionToBackEnd(this.pushSubscription).subscribe();
        })
        .catch(console.error);
    }
  }

  addToFavourite(eventCategory: EventCategory) {
    this.eventCategoryService.addToFavourite(eventCategory).subscribe(
      response => console.log('YEA added new category!' + response)
    );
    this.subscribeToNotifications();

    this.routerLink.navigateByUrl('/home');
  }
}
