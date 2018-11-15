import { Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {EventService} from '../event.service';
import {Event} from '../model/event.model';
import {MessageService} from '../../services/message.service';

@Component({
  selector: 'app-event-detail',
  templateUrl: './event-detail.component.html',
  styleUrls: ['./event-detail.component.css']
})
export class EventDetailComponent implements OnInit {

  isThisMyEvent:boolean = false;
  event: Event;

  constructor(private route: ActivatedRoute,
              private eventService: EventService,
              private router: Router,
              private messageService: MessageService) {  }

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
    this.messageService.confirm(
      'Delete Event', 'Do you want delete this event?', 'Yes', 'No'
    ).subscribe(
      confirmed => {
        if(confirmed) {
          const id = +this.route.snapshot.paramMap.get('id');
          this.eventService.deleteEventByID(id)
            .subscribe(
              response => {
                this.router.navigateByUrl('/home');
                console.log("success" + response);
              },
              error => console.log("error" + error)
            );
        }
      }
    );
  }


}
