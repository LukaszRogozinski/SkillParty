import {Component, OnInit} from '@angular/core';
import {EventCategory} from '../model/event-category.model';
import {EventCategoryService} from '../event-category.service';
import {Router} from '@angular/router';
import {MessageService} from '../../services/message.service';

@Component({
  selector: 'app-event-category-list',
  templateUrl: './event-category-list.component.html',
  styleUrls: ['./event-category-list.component.css']
})
export class EventCategoryListComponent implements OnInit {

  eventCategories: EventCategory[];

  constructor(private eventCategoryService: EventCategoryService,
              private routerLink: Router,
              private messageService: MessageService) {
  }

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
      () => this.messageService.success("Successfully added new favourite category!")
    );
    this.routerLink.navigateByUrl('/home');
  }
}
