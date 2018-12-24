import {Component, OnInit} from '@angular/core';
import {Event} from '../model/event.model';
import {EventService} from '../event.service';

@Component({
  selector: 'app-my-event-list',
  templateUrl: './my-event-list.component.html',
  styleUrls: ['./my-event-list.component.css']
})
export class MyEventListComponent implements OnInit {

  events: Event[];

  constructor(private eventService: EventService) {
  }

  ngOnInit() {
    this.eventService.getMyEvents().subscribe(
      response => {
        this.events = response;
      },
      (error) => console.log(error)
    );
  }


}
