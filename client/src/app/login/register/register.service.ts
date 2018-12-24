import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {NewUserModel} from './model/new-user.model';
import {HttpService} from '../../services/http.service';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private httpService: HttpService) {
  }

  private registerUrl = 'http://localhost:8080/register';

  public registerNewUser(newUser: NewUserModel): Observable<any> {
    return this.httpService.post(this.registerUrl + '/new', newUser);
  }

}
