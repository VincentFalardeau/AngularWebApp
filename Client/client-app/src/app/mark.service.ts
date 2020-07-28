import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Mark } from './mark'
import { MessageService } from './message.service';
import { MessageObject } from './message-object';

@Injectable({
  providedIn: 'root'
})
export class MarkService {

  private marksURL = "http://127.0.0.1:8080/marks"

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  getMarks(courseId: number): Observable<Mark[]> {
    return this.http.get<Mark[]>('http://127.0.0.1:8080/courses/'+courseId+'/marks').pipe( 
      catchError(this.handleError<Mark[]>('getMarks', []))
    );
  }

  updateMark(mark: Mark): Observable<any> {
    return this.http.put(this.marksURL + '/' + mark.id, mark).pipe(
      tap(_ => this.log('Successfully updated mark ' + mark.description, true)),
      catchError(this.handleError<any>('updateMark'))
    );
  
  }

  addMark(mark: Mark): Observable<any> {
    return this.http.post(this.marksURL, mark).pipe(
      tap(_ => this.log('Added mark '+mark.description+' in course ' + mark.course.code, true)),
      catchError(this.handleError<any>('addMark'))
    );
  }

  deleteMark(mark: Mark): Observable<any> {
    return this.http.delete(this.marksURL + '/' + mark.id).pipe(
      tap(_ => this.log('Deleted mark ' + mark.description, true)),
      catchError(this.handleError<any>('deleteMark'))
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


