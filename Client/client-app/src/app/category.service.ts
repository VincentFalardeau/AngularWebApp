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
      catchError(this.handleError<Category[]>([]))
    );    
  }

  //Handle the http operation that failed

  /*T is a template type, can be anything*/
  private handleError<T>(result?: T /*? means the result parameter is optional*/) {

    //Convert error's type to any, and then to an Observable of type T (template)
    return (error: any): Observable<T> => {

      // Log to console (for the developers)
      console.error(error); 

      this.log('Application error, please contact the administrator.', false);

      // Let the app keep running by returning an empty result.
      return of(result as T); //of: Returns an Observable instance that synchronously delivers the values provided as arguments.
    };
  }

  private log(message: string, success = true) {
    this.messageService.add(new MessageObject(message, success));
  }
}
