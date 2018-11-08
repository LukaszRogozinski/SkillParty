import {EventEmitter, Injectable} from '@angular/core';
import {Subject} from 'rxjs';

@Injectable()
export class IsLoggedService {

  statusUpdated = new EventEmitter<boolean>();
  statusUpdatednew = new Subject();

    constructor(){}

}
