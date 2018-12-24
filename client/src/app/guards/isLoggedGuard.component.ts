import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {TokenStorage} from '../core/token.storage';
import {IsLoggedService} from '../services/is-logged.service';
import {MessageService} from '../services/message.service';

@Injectable()
export class IsLoggedGuardComponent implements CanActivate {

  constructor(private isLoggedService: IsLoggedService,
              private tokenStorage: TokenStorage,
              private router: Router,
              private messageService: MessageService) {

  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (this.tokenStorage.getToken()) {
      return true;
    } else {
      this.router.navigate(['login']);
      this.messageService.error('Session expired');
      return false;
    }
  }

}
