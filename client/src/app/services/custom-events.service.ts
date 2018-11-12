import {EventEmitter, Injectable} from '@angular/core';
import {Subject} from 'rxjs';

@Injectable()
export class CustomEventsService {

  showLoggedUserDetails = new Subject();
  showLoggedUserDetailsEvent = new EventEmitter<boolean>();

  constructor(){}

}
