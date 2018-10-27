import { Component, OnInit } from '@angular/core';
import {EventCategory} from '../model/event-category.model';
import {EventCategoryService} from '../event-category.service';
import {Router} from '@angular/router';
import * as Stomp from 'stompjs';

@Component({
  selector: 'app-event-category-list',
  templateUrl: './event-category-list.component.html',
  styleUrls: ['./event-category-list.component.css']
})
export class EventCategoryListComponent implements OnInit {

  eventCategories: EventCategory[];


  ws: any;
  name: string;
  disabled: boolean;

  constructor(private eventCategoryService: EventCategoryService,
              private routerLink: Router) { }

  ngOnInit() {
    this.eventCategoryService.getEventCategories().subscribe(
      data => {
        this.eventCategories = data;
      }
    );
  }

  addToFavourite(eventCategory: EventCategory) {
    this.eventCategoryService.addToFavourite(eventCategory).subscribe();
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
       // that.showGreeting(message.body);
      });
      that.disabled = true;
    }, function(error) {
      alert("STOMP error " + error);
    });
  }

}
