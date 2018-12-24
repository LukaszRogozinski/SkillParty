import {Component, OnInit} from '@angular/core';
import {Event} from '../model/event.model';
import {EventService} from '../event.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-event-edit',
  templateUrl: './event-edit.component.html',
  styleUrls: ['./event-edit.component.css']
})
export class EventEditComponent implements OnInit {

  event: Event;

  constructor(private route: ActivatedRoute,
              private eventService: EventService) {
  }

  ngOnInit() {
    this.getEvent();
  }

  getEvent(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.eventService.getEventById(id).subscribe(
      response => {
        this.event = response;
      },
      (error) => console.log(error)
    );
  }

  editEvent() {

  }

}
