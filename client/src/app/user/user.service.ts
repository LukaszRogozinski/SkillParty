import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {User} from './model/user.model';
import {HttpService} from '../services/http.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpService: HttpService) {
  }

  private userUrl = 'http://localhost:8080/user';

  public getUsers(): Observable<any> {
    return this.httpService.get(this.userUrl + '/all');
  }


  public getUserDetailByUsername(username: string): Observable<any> {
    return this.httpService.get(this.userUrl + '/detail/' + username);
  }

  public deleteSelectedUserByUsername(username: String): Observable<any> {
    return this.httpService.delete(this.userUrl + '/delete/' + username);
  }

  public saveUser(user: User, username: string): Observable<any> {
    return this.httpService.put(this.userUrl + '/update/' + username, user);
  }
}
