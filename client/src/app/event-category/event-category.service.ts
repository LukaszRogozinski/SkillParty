import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {EventCategory} from './model/event-category.model';

@Injectable({
  providedIn: 'root'
})
export class EventCategoryService {

  constructor(private httpClient: HttpClient) { }

  private eventCategoryUrl = 'http://localhost:8080/eventCategory';

  public getEventCategories(): Observable<EventCategory[]> {
    return this.httpClient.get<EventCategory[]>(this.eventCategoryUrl + '/all');
  }

  public addToFavourite(eventCategory: EventCategory): Observable<EventCategory> {
   return this.httpClient.post<EventCategory>(this.eventCategoryUrl + '/add', eventCategory);
  }
}
