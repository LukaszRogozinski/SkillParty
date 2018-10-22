import { Component, OnInit } from '@angular/core';
import {Event} from '../model/event.model';
import {EventService} from '../event.service';

@Component({
  selector: 'app-event-edit',
  templateUrl: './event-edit.component.html',
  styleUrls: ['./event-edit.component.css']
})
export class EventEditComponent implements OnInit {

  constructor(  private eventService: EventService) { }

  ngOnInit() {
  }



}
