import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {HttpService} from '../services/http.service';
import {TokenStorage} from './token.storage';
import {IsLoggedService} from '../services/is-logged.service';
import {Router} from '@angular/router';
import {MessageService} from '../services/message.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  roles = {ROLE_ADMIN: false, ROLE_USER: false};
  rolesArray = [];
  username: string;

  constructor(private httpService: HttpService, private tokenStorage: TokenStorage, private router: Router,
              private isLoggedService: IsLoggedService, private messageService: MessageService) {
  }

  attemptAuth(ussername: string, password: string): Observable<any> {
    const credentials = {username: ussername, password: password};
    console.log('attempAuth ::');
    return this.httpService.post<any>('http://localhost:8080/token/generate-token', credentials);
  }

  isLoggedUser() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedService.statusUpdated.next(true);
      return true;
    } else {
      this.isLoggedService.statusUpdated.next(false);
      return false;
    }
  }

  logout(): void {
    this.tokenStorage.signOut();
    this.isLoggedService.statusUpdated.next(false);
    this.router.navigate(['login']);
    this.messageService.info('Logged out');
  }

  public initRoles() {
    const decodedToken = this.tokenStorage.getDecodedToken();
    this.rolesArray = decodedToken.scopes.split(',');
    Object.entries(this.roles).forEach(([key, value]) => this.roles[key] = this.hasRole(key));

  }

  public resetRoles() {
    Object.entries(this.roles).forEach(([key, value]) => this.roles[key] = false);
  }

  hasRole(role: string): boolean {
    for (let i = 0; i < this.rolesArray.length; i++) {
      if (this.rolesArray[i] === role) {
        return true;
      }
    }
    return false;
  }
}
