import {EventEmitter, Injectable} from '@angular/core';
import {Subject} from 'rxjs';

@Injectable()
export class IsLoggedUserEventService {

  isMyEvent = new Subject();

  constructor(){}

}
