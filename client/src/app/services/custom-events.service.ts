import { Injectable} from '@angular/core';
import {Subject} from 'rxjs';

@Injectable()
export class CustomEventsService {

  isMyEvent = new Subject();

  constructor(){}

}
