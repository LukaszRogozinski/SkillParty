import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {EventService} from '../event.service';

@Component({
  selector: 'app-event-detail',
  templateUrl: './event-detail.component.html',
  styleUrls: ['./event-detail.component.css']
})
export class EventDetailComponent implements OnInit {

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
  }

  getEvent(): void {
    const id = +this.route.snapshot.paramMap.get('id');
  }

}
