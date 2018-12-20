import {Component, OnInit, ViewChild} from '@angular/core';
import {NewEvent} from '../model/new-event.model';
import {EventService} from '../event.service';
import * as Stomp from 'stompjs';
import {CanComponentDeactivate} from '../../guards/can-deactivate-guard.service';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {NgForm} from '@angular/forms';
import {EventCategory} from '../../event-category/model/event-category.model';
import {EventCategoryService} from '../../event-category/event-category.service';
import {PushNotificationService} from '../../services/push-notification.service';

@Component({
  selector: 'app-new-event',
  templateUrl: './new-event.component.html',
  styleUrls: ['./new-event.component.css']
})
export class NewEventComponent implements OnInit, CanComponentDeactivate {

  eventCategories: EventCategory[];
  @ViewChild('f') signupForm: NgForm;
  newEvent: NewEvent = new NewEvent();
  ws: any;
  name: string;
  changesSaved = false;

  constructor(private eventService: EventService,
              private eventCategoryService: EventCategoryService,
              private router: Router,
              private pushService: PushNotificationService) { }

  ngOnInit() {

    this.getEventCategories();
    this.newEvent.averageVote = 0;
  }

  getEventCategories(){
    this.eventCategoryService.getEventCategories().subscribe(
      response => this.eventCategories = response,
      error => console.log(error)
    );
  }

  sendNotifications() {
    this.pushService.getSubscriptionsFromDB().subscribe();
    this.pushService.sendNotification().subscribe();
  }

  addEvent(){
    this.eventService.add(this.newEvent)
      .subscribe(
        (response) => console.log(response),
        (error) => console.log(error)
      );
    this.sendNotifications();
    this.changesSaved = true;
    this.router.navigateByUrl('/home');
  }

  canDeactivate(): Observable<boolean> | Promise<boolean> | boolean {
    if((this.newEvent.name !== null) && !this.changesSaved){
      return confirm('Do you want to discard the changes?');
    } else {
      return true;
    }
  }

}
