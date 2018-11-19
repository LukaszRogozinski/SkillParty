import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Event} from './model/event.model';
import {Router} from '@angular/router';
import {NewEvent} from './model/new-event.model';
import {Observable} from 'rxjs';
import {HttpService} from '../services/http.service';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  private eventUrl = 'http://localhost:8080/event';

  constructor(private httpService: HttpService,
              private router: Router) { }

  public add(event: NewEvent) {
    return this.httpService.post<NewEvent>(this.eventUrl + '/add', event);
  }

  public getEvents(): Observable<Event[]> {
    return this.httpService.get<Event[]>(this.eventUrl + '/all');
  }

  public getMyEvents(): Observable<Event[]> {
    return this.httpService.get<Event[]>(this.eventUrl + '/all-mine-events');
  }

  public getEventById(id: number): Observable<Event> {
    return this.httpService.get<Event>(this.eventUrl + '/detail/' + id);
  }

  public deleteEventByID(id: number): Observable<any> {
    return this.httpService.delete(this.eventUrl + '/detail/' + id + '/delete');
  }
}
