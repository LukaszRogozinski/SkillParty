import { Component, OnInit } from '@angular/core';
import {NewEvent} from '../model/new-event.model';
import {EventService} from '../event.service';
import * as Stomp from 'stompjs';

@Component({
  selector: 'app-new-event',
  templateUrl: './new-event.component.html',
  styleUrls: ['./new-event.component.css']
})
export class NewEventComponent implements OnInit {

  newEvent: NewEvent = new NewEvent();
  greetings: string[] = [];
  showConversation: boolean = false;
  ws: any;
  name: string;
  disabled: boolean;

  constructor(private eventService: EventService) { }

  ngOnInit() {
    this.connect();
    this.newEvent.averageVote = 0;
  }

  addEvent(){

    this.eventService.add(this.newEvent);
    this.sendName();
  }

  sendName() {

    let data = JSON.stringify({
      'name' : "jacek"
    })
    this.ws.send("/app/sportMessage", {}, data);
  }

  connect() {
    //connect to stomp where stomp endpoint is exposed
    //let ws = new SockJS(http://localhost:8080/greeting);
    let socket = new WebSocket("ws://localhost:8080/sport");
    this.ws = Stomp.over(socket);
    let that = this;
    this.ws.connect({}, function(frame) {
      that.ws.subscribe("/errors", function(message) {
        alert("Error " + message.body);
      });
      that.ws.subscribe("/sportTopic/reply", function(message) {
        console.log(message)
     //   that.showGreeting(message.body);
      });
      that.disabled = true;
    }, function(error) {
      alert("STOMP error " + error);
    });
  }

}
