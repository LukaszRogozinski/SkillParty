import {EventEmitter, Injectable} from '@angular/core';

@Injectable()
export class IsLoggedService {

  statusUpdated = new EventEmitter<boolean>( );

    constructor(){}

}
