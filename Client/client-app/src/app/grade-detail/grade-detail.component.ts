import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';

import { Grade } from '../grade';

//Component that displays details for a specified grade.

@Component({
  selector: 'app-grade-detail',
  templateUrl: './grade-detail.component.html',
  //Importing CourseDetailComponent's stylesheet
  styleUrls: ['../course-detail/course-detail.component.css', './grade-detail.component.css']
})
export class GradeDetailComponent implements OnInit {

  //The detailed grade.
  @Input() grade: Grade;

  //To indicate the closing of the component.
  @Output() close = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

  //Closes the component
  closeComponent(): void{
    this.grade = null;

    //Emit the close event.
    this.close.emit();
  }

}
