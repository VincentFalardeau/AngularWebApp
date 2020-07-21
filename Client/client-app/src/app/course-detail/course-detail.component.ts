import { Component, OnInit, Input } from '@angular/core';

import { Course } from '../course'

//Component that displays a course's details.

@Component({
  selector: 'app-course-detail',
  templateUrl: './course-detail.component.html',
  styleUrls: ['./course-detail.component.css']
})
export class CourseDetailComponent implements OnInit {

  //The detailed course.
  @Input() course: Course;

  constructor() { }

  ngOnInit(): void {
  }

}
