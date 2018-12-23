import { Component, OnInit } from '@angular/core';
import {EventCategory} from '../model/event-category.model';
import {EventCategoryService} from '../event-category.service';
import {Router} from '@angular/router';

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
      response => {
        this.eventCategories = response;
      },
      (error) => console.log(error)
    );
  }

  addToFavourite(eventCategory: EventCategory) {
    this.eventCategoryService.addToFavourite(eventCategory).subscribe(
      response => console.log('YEA added new category!' + response)
    );
    this.routerLink.navigateByUrl('/home');
  }
}
