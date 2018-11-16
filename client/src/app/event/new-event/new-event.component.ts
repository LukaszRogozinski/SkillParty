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
  disabled: boolean;
  changesSaved = false;

  constructor(private eventService: EventService,
              private eventCategoryService: EventCategoryService,
              private router: Router) { }

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

  addEvent(){
    this.connect();
    this.eventService.add(this.newEvent)
      .subscribe(
        (response) => console.log(response),
        (error) => console.log(error)
      );
    let a = this.ws.connected;
      this.sendName();
    this.changesSaved = true;
    this.router.navigateByUrl('/home');
  }

  sendName() {
    let data = JSON.stringify({
      'title' : this.newEvent.eventCategory.toLocaleLowerCase() + " event",
      'body' : 'You have new ' +  this.newEvent.eventCategory.toLowerCase() + ' event',
      'type' : 'Info'
    })
    this.ws.ws.onopen = () => this.ws.send("/app/" + this.newEvent.eventCategory.toLowerCase() + "Message", {}, data);
  }

  connect() {
    let socket = new WebSocket("ws://localhost:8080/" + this.newEvent.eventCategory.toLowerCase());
    this.ws = Stomp.over(socket );

    let that = this;
    this.ws.connect({}, function(frame) {
      that.ws.subscribe("/errors", function(message) {
        alert("Error " + message.body);
      });
        that.ws.subscribe("/" + that.newEvent.eventCategory.toLowerCase() + "Topic/reply", function(message) {
        console.log(message)
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
