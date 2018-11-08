import {Component, OnInit, ViewChild} from '@angular/core';
import {NewEvent} from '../model/new-event.model';
import {EventService} from '../event.service';
import * as Stomp from 'stompjs';
import {CanComponentDeactivate} from '../../guards/can-deactivate-guard.service';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-new-event',
  templateUrl: './new-event.component.html',
  styleUrls: ['./new-event.component.css']
})
export class NewEventComponent implements OnInit, CanComponentDeactivate {

  @ViewChild('f') signupForm: NgForm;
  newEvent: NewEvent = new NewEvent();
  ws: any;
  name: string;
  disabled: boolean;
  allowEdit = false;
  changesSaved = false;

  constructor(private eventService: EventService,
              private router: Router) { }

  ngOnInit() {
    this.connect();
    this.newEvent.averageVote = 0;
  }

  addEvent(){

    this.eventService.add(this.newEvent)
      .subscribe(
        (response) => console.log(response),
        (error) => console.log(error)
      );
    this.sendName();
    this.changesSaved = true;
    this.router.navigateByUrl('/home');
  }

  sendName() {

    let data = JSON.stringify({
      'name' : "jacek"
    })
   // this.ws.send("/app/sportMessage", {});
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

  canDeactivate(): Observable<boolean> | Promise<boolean> | boolean {
    if((this.newEvent.name !== null) && !this.changesSaved){
      return confirm('Do you want to discard the changes?');
    } else {
      return true;
    }
  }

}
