import { Component, OnInit } from '@angular/core';
import {EventCategory} from '../model/event-category.model';
import {EventCategoryService} from '../event-category.service';
import {Router} from '@angular/router';
import * as Stomp from 'stompjs';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NotificationsService } from 'angular2-notifications';

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

    const temp = this.form.getRawValue();
    const title = 'Sport event';//temp.title;
    const content = message;//temp.content;
    const type = temp.type;

    delete temp.title;
    delete temp.content;
    delete temp.type;
    this._notifications.create(title, content, type, temp)
  }

  addToFavourite(eventCategory: EventCategory) {
    this.eventCategoryService.addToFavourite(eventCategory).subscribe(
      response => console.log('YEA added new category!' + response)
    );
    this.connect(eventCategory.name);
    this.routerLink.navigateByUrl('/home');
  }

  connect(eventCategoryName: String) {
    //connect to stomp where stomp endpoint is exposed
    //let ws = new SockJS(http://localhost:8080/greeting);
    let socket = new WebSocket("ws://localhost:8080/" + eventCategoryName.toLowerCase());
    this.ws = Stomp.over(socket);
    let that = this;
    this.ws.connect({}, function(frame) {
      that.ws.subscribe("/errors", function(message) {
        alert("Error " + message.body);
      });
      that.ws.subscribe("/sportTopic/reply", function(message) {
        console.log(message)
        that.create(message.body)
       // that.showGreeting(message.body);
      });
      that.disabled = true;
    }, function(error) {
      alert("STOMP error " + error);
    });
  }

}
