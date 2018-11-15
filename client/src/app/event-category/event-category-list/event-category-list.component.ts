import { Component, OnInit } from '@angular/core';
import {EventCategory} from '../model/event-category.model';
import {EventCategoryService} from '../event-category.service';
import {Router} from '@angular/router';
import * as Stomp from 'stompjs';
import { FormBuilder, FormGroup } from '@angular/forms';
import {NotificationsService, NotificationType} from 'angular2-notifications';

@Component({
  selector: 'app-event-category-list',
  templateUrl: './event-category-list.component.html',
  styleUrls: ['./event-category-list.component.css']
})
export class EventCategoryListComponent implements OnInit {

  eventCategories: EventCategory[];

  form: FormGroup;

  ws: any;
  name: string;
  disabled: boolean;

  constructor(private eventCategoryService: EventCategoryService,
              private routerLink: Router,
              private _notifications: NotificationsService,
              private _fb: FormBuilder) { }

  ngOnInit() {
    this.eventCategoryService.getEventCategories().subscribe(
      response => {
        this.eventCategories = response;
      },
      (error) => console.log(error)
    );

    this.form = this._fb.group({
      type: 'info',
      title: 'This is just a title',
      content: 'This is just some content',
      timeOut: 5000,
      showProgressBar: true,
      pauseOnHover: true,
      clickToClose: true,
      animate: 'fromRight'
    });
  }

  create(message) {

    let messageBody : {title: string, body: string, type: string};
    messageBody = JSON.parse(message);
    const temp = this.form.getRawValue();
    const title = messageBody.title;
    const content = messageBody.body;
    const type = NotificationType[messageBody.type];

    delete temp.title;
    delete temp.content;
    delete temp.type;
    this._notifications.create(title, content, type, temp)
  }

  addToFavourite(eventCategory: EventCategory) {
    this.eventCategoryService.addToFavourite(eventCategory).subscribe(
      response => console.log('YEA added new category!' + response)
    );
    this.connect(eventCategory);
    this.routerLink.navigateByUrl('/home');
  }

  connect(eventCategoryName: EventCategory) {
    //connect to stomp where stomp endpoint is exposed
    //let ws = new SockJS(http://localhost:8080/greeting);
    let socket = new WebSocket("ws://localhost:8080/" + eventCategoryName.name.toLowerCase());
    this.ws = Stomp.over(socket);
    let that = this;
    this.ws.connect({}, function(frame) {
      that.ws.subscribe("/errors", function(message) {
        alert("Error " + message.body);
      });
      that.ws.subscribe("/" + eventCategoryName.name.toLowerCase() +  "Topic/reply", function(message) {
        console.log(message)
        that.create(message.body)
      });
      that.disabled = true;
    }, function(error) {
      alert("STOMP error " + error);
    });
  }
}
