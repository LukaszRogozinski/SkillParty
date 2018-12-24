import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';

@Injectable()
export class IsLoggedService {

  statusUpdated = new Subject();

  constructor() {
  }

}
