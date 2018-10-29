import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Event} from './model/event.model';
import {Router} from '@angular/router';
import {NewEvent} from './model/new-event.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  private eventUrl = 'http://localhost:8080/event';

  constructor(private http: HttpClient,
              private router: Router) { }

  public add(event: NewEvent) {
    this.http.post<NewEvent>(this.eventUrl + '/add', event)
      .subscribe();
  }

  public getEvents(): Observable<Event[]> {
    return this.http.get<Event[]>(this.eventUrl + '/all');
  }

  public getEventById(id: number): Observable<Event> {
    return this.http.get<Event>(this.eventUrl + '/detail/' + id);
  }
}
