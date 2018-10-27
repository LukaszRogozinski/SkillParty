import { Component, OnInit } from '@angular/core';
import {Event} from '../model/event.model';
import {EventService} from '../event.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NotificationsService } from 'angular2-notifications';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css']
})
export class EventListComponent implements OnInit {

  events: Event[];

  form: FormGroup;

  types = ['alert', 'error', 'info', 'warn', 'success'];
  animationTypes = ['fromRight', 'fromLeft', 'scale', 'rotate'];


  constructor(private eventService: EventService,
              private _notifications: NotificationsService,
              private _fb: FormBuilder) { }

  ngOnInit() {
    this.eventService.getEvents().subscribe(
      data => {
        this.events = data;
      }
      );

    this.form = this._fb.group({
      type: 'success',
      title: 'This is just a title',
      content: 'This is just some content',
      timeOut: 5000,
      showProgressBar: true,
      pauseOnHover: true,
      clickToClose: true,
      animate: 'fromRight'
    });
  }

  create() {

    const temp = this.form.getRawValue();
    const title = temp.title;
    const content = temp.content;
    const type = temp.type;

  /*  delete temp.title;
    delete temp.content;
    delete temp.type;*/
    this._notifications.create(title, content, type, temp)
  }

}
