import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Category } from './category'
import { MessageService } from './message.service';
import { MessageObject } from './message-object';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  categories: Category[];

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>('http://127.0.0.1:8080/categories').pipe(
      catchError(this.handleError<Category[]>('getCategories', []))
    );    
  }

  /**
  * Handle Http operation that failed.
  * Let the app continue.
  * @param operation - name of the operation that failed
  * @param result - optional value to return as the observable result
  */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log('Application error, please contact your administrator.', false);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  private log(message: string, success: boolean) {
    this.messageService.add(new MessageObject(message, success));
  }
}
