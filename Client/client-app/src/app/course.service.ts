import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Course } from './course'
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  private courseURL = 'http://127.0.0.1:8080/courses';

  courses: Course[];

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  getCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(this.courseURL).pipe( 
      tap(_ => this.log('fetched courses')),
      catchError(this.handleError<Course[]>('getCourses', []))
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
    //this.messageService.add(`GlobalCourseService: ${message}`);
  }
}
