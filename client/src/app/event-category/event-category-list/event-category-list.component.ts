import { Component, OnInit } from '@angular/core';
import {EventCategory} from '../model/event-category.model';
import {EventCategoryService} from '../event-category.service';
import {Router, RouterLink} from '@angular/router';

@Component({
  selector: 'app-event-category-list',
  templateUrl: './event-category-list.component.html',
  styleUrls: ['./event-category-list.component.css']
})
export class EventCategoryListComponent implements OnInit {

  eventCategories: EventCategory[];

  constructor(private eventCategoryService: EventCategoryService,
              private routerLink: Router) { }

  ngOnInit() {
    this.eventCategoryService.getEventCategories().subscribe(
      data => {
        this.eventCategories = data;
      }
    );
  }

  addToFavourite(eventCategory: EventCategory) {
    this.eventCategoryService.addToFavourite(eventCategory);
    this.routerLink.navigateByUrl('/home');
  }

}
