import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Mark } from './mark'
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class MarkService {

  private marksURL = 'http://127.0.0.1:8080/courses/1/marks';

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  getMarks(): Observable<Mark[]> {
    //this.log('fetched marks');
    return this.http.get<Mark[]>(this.marksURL).pipe( 
      tap(_ => this.log('fetched marks')),
      catchError(this.handleError<Mark[]>('getMarks', []))
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
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  private log(message: string) {
    this.messageService.add(`MarkService: ${message}`);
  }
}


