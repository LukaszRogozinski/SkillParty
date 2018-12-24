import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {MessageService} from './message.service';
import {HttpErrorResponse} from '@angular/common/http';
import {ErrorObservable} from 'rxjs/observable/ErrorObservable';
import {TokenStorage} from '../core/token.storage';
import {IsLoggedService} from './is-logged.service';


@Injectable()
export class HttpErrorHandler {

  private badRequest = 400;
  private notAuthorizedUserErrorCode = 401;
  private forbiddenErrorCode = 403;
  private internalServerError = 500;

  constructor(private router: Router,
              private messageService: MessageService,
              private tokenStorage: TokenStorage,
              private isLoggedService: IsLoggedService) {

  }

  handleError(error: HttpErrorResponse): ErrorObservable<any> {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      if (error.status === this.notAuthorizedUserErrorCode) {
        this.messageService.error('Your session has expired.');
        this.isLoggedService.statusUpdated.next(false);
        this.router.navigate(['login']);
      } else if (error.status === this.internalServerError) {
        this.messageService.error('Failed to load this source');
        this.router.navigate(['home']);
      } else if (error.status === this.forbiddenErrorCode && error.error["BadCredentialsException"] === "Bad credentials") {
        this.messageService.error('Invalid username or password');
      } else if (error.status === this.forbiddenErrorCode) {
        this.messageService.error('Source not allowed');
      } else if (error.status === this.badRequest) {
        this.messageService.error('The page do not exist');
      } else {
        console.error(`Backend returned code ${error.status}, ` +
          `body was: ${error.error}`);
      }
    }

    return new ErrorObservable(error.error);
  }
}
