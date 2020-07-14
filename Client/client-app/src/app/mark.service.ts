import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Mark } from './mark'
import { MarkData } from './mark-data'
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class MarkService {

  //private marksURL = 'http://127.0.0.1:8080/courses/1/marks';
  private marksURL = "http://127.0.0.1:8080/marks"

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  getMarks(courseId: number): Observable<Mark[]> {
    return this.http.get<Mark[]>('http://127.0.0.1:8080/courses/'+courseId+'/marks').pipe( 
      tap(_ => this.log('fetched marks for course having id = ' + courseId)),
      catchError(this.handleError<Mark[]>('getMarks', []))
    );
  }

  updateMarks(marks: Mark[]): Observable<any> {
    return this.http.put(this.marksURL, marks).pipe(
      tap(_ => this.log('updated marks')),
      catchError(this.handleError<any>('updateMarks'))
    );
  
  }

  addMark(mark: Mark): Observable<any> {
    return this.http.post(this.marksURL, mark).pipe(
      tap(_ => this.log('added marks')),
      catchError(this.handleError<any>('addMark'))
    );
  
  }

  deleteMark(id: number): Observable<any> {
    return this.http.delete(this.marksURL + '/' + id).pipe(
      tap(_ => this.log('deleted marks having id = ' + id)),
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
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  private log(message: string) {
    this.messageService.add(`MarkService: ${message}`);
  }
}


