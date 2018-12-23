import {Component, OnInit, ViewChild} from '@angular/core';
import {NewEvent} from '../model/new-event.model';
import {EventService} from '../event.service';
import * as Stomp from 'stompjs';
import {CanComponentDeactivate} from '../../guards/can-deactivate-guard.service';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {NgForm} from '@angular/forms';
import {EventCategory} from '../../event-category/model/event-category.model';
import {EventCategoryService} from '../../event-category/event-category.service';
import {EmailService} from '../../services/email.service';

export class EmailMessage {
  subject: string;
  email: string;
  feedback: string;
  eventCategory: string;
}

@Component({
  selector: 'app-new-event',
  templateUrl: './new-event.component.html',
  styleUrls: ['./new-event.component.css']
})
export class NewEventComponent implements OnInit, CanComponentDeactivate {

  eventCategories: EventCategory[];
  newEvent: NewEvent = new NewEvent();
  changesSaved = false;
  emailMessage: EmailMessage;

  constructor(private eventService: EventService,
              private eventCategoryService: EventCategoryService,
              private router: Router,
              private emailService: EmailService) {
  }

  ngOnInit() {

    this.getEventCategories();
    this.newEvent.averageVote = 0;
    this.emailMessage = new EmailMessage();
    this.emailMessage.email = 'noreply@eventshare.com';
    this.emailMessage.feedback = 'New event that might interested you had been added. Check it out before it\'s to late!';
  }

  getEventCategories() {
    this.eventCategoryService.getEventCategories().subscribe(
      response => this.eventCategories = response,
      error => console.log(error)
    );
  }

  addEvent() {
    this.eventService.add(this.newEvent)
      .subscribe(
        (response) => console.log(response),
        (error) => console.log(error)
      );
    this.changesSaved = true;
    this.emailMessage.eventCategory = this.newEvent.eventCategory;
    this.emailMessage.subject = 'New ' + this.newEvent.eventCategory.toLowerCase() + ' event!';
    this.emailService.sendEmails(this.emailMessage).subscribe(
      response => console.log(response)
    );
    this.router.navigateByUrl('/home');
  }

  canDeactivate(): Observable<boolean> | Promise<boolean> | boolean {
    if ((this.newEvent.name !== null) && !this.changesSaved) {
      return confirm('Do you want to discard the changes?');
    } else {
      return true;
    }
  }

}
