/*
import { Component, OnInit } from '@angular/core';
import {Event} from './model/event.model';
import {TokenStorage} from '../core/token.storage';
import {EventService} from './event.service';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {

//  event = new Event();
  event: Event;

  constructor(private tokenStorage: TokenStorage,
              private eventService: EventService) { }

  ngOnInit() {
    this.event.token = this.tokenStorage.getToken();
  }

  addEvent(): void {
      this.eventService.add(this.event);
  }

}
*/
