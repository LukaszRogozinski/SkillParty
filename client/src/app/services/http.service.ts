import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {HttpErrorHandler} from './http-error-handler.service';

@Injectable()
export class HttpService {

  constructor(private http: HttpClient,
              private httpErrorHandler: HttpErrorHandler) {
  }

  get<T>(endpoint: string): Observable<T> {
    return this.http.get<T>(endpoint).pipe(
      catchError(err => this.httpErrorHandler.handleError(err))
    );
  }

  post<T>(endpoint: string, body): Observable<T> {
    return this.http.post<T>(endpoint, body).pipe(
      catchError(err => this.httpErrorHandler.handleError(err))
    );
  }

  put<T>(endpoint: string, body): Observable<T> {
    return this.http.put<T>(endpoint, body).pipe(
      catchError(err => this.httpErrorHandler.handleError(err))
    );
  }

  delete<T>(endpoint: string): Observable<T> {
    return this.http.delete<T>(endpoint).pipe(
      catchError(err => this.httpErrorHandler.handleError(err))
    );
  }
}
