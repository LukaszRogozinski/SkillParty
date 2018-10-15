import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Event} from './model/event.model';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  private eventUrl = 'http://localhost:8080/event';

  constructor(private http: HttpClient,
              private router: Router) { }

  public add(event: Event) {
    this.http.post<Event>(this.eventUrl + '/add', event)
      .subscribe(resp => {this.router.navigateByUrl('/users')
      });
  }
}
