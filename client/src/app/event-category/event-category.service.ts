import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {EventCategory} from './model/event-category.model';
import {HttpService} from '../services/http.service';

@Injectable({
  providedIn: 'root'
})
export class EventCategoryService {

  constructor(private httpService: HttpService) { }

  private eventCategoryUrl = 'http://localhost:8080/eventCategory';

  public getEventCategories(): Observable<EventCategory[]> {
    return this.httpService.get<EventCategory[]>(this.eventCategoryUrl + '/all');
  }

  public addToFavourite(eventCategory: EventCategory): Observable<any> {
   return this.httpService.post(this.eventCategoryUrl + '/add', eventCategory);
  }
}
