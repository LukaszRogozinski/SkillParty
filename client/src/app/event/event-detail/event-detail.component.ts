import { Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {EventService} from '../event.service';
import {Event} from '../model/event.model';

@Component({
  selector: 'app-event-detail',
  templateUrl: './event-detail.component.html',
  styleUrls: ['./event-detail.component.css']
})
export class EventDetailComponent implements OnInit {

  isThisMyEvent:boolean = false;
  event: Event;

  constructor(private route: ActivatedRoute,
              private eventService: EventService) {  }

  ngOnInit() {
    this.getEvent();
  }

  getEvent(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.eventService.getEventById(id).subscribe(
      response => {
        this.event = response;
        if(this.event.isEventLoggedUser){
          this.isThisMyEvent = true;
        }
      },
      (error) => console.log(error)
    );
  }

  deleteEvent(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.eventService.deleteEventByID(id)
      .subscribe(
        response => {
          console.log(response);
        },
        error => console.log(error)
      );
  }
}
