import { Component, OnInit } from '@angular/core';
import {NewEvent} from '../model/new-event.model';
import {EventService} from '../event.service';

@Component({
  selector: 'app-new-event',
  templateUrl: './new-event.component.html',
  styleUrls: ['./new-event.component.css']
})
export class NewEventComponent implements OnInit {

  newEvent: NewEvent = new NewEvent();

  constructor(private eventService: EventService) { }

  ngOnInit() {

    this.newEvent.averageVote = 0;
  }

  addEvent(){
    this.eventService.add(this.newEvent);
  }

}
