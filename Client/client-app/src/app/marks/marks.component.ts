import { Component, OnInit } from '@angular/core';

import { Mark } from '../mark'
import { MarkService } from '../mark.service';
import { MessageService } from '../message.service';
import { Course } from '../course'
import { CourseService } from '../course.service';

@Component({
  selector: 'app-marks',
  templateUrl: './marks.component.html',
  styleUrls: ['./marks.component.css']
})
export class MarksComponent implements OnInit {

  marks: Mark[];
  selectedMark: Mark;
  courses: Course[];
  selectedCourseId: number;

  onSelect(mark: Mark): void {
    this.selectedMark = mark;
    this.messageService.add(`MarksComponent: Selected mark id=${mark.id}`);
  }

  constructor(private markService: MarkService, private messageService: MessageService, private courseService: CourseService) {}

  ngOnInit(): void {
    this.courseService.getCourses().subscribe(courses => {
      this.courses = courses;
      this.selectedCourseId = this.courses[0].id;
      this.getMarks(this.selectedCourseId);
    }); 
  }

  onChange(courseId: number): void{
    this.getMarks(courseId);
  }

  getMarks(courseId: number): void {
    this.markService.getMarks(courseId).subscribe(marks => this.marks = marks);
  }

  

}
