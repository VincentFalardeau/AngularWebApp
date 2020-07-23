import { Component, OnInit } from '@angular/core';

import { Mark } from '../mark'
import { MarkService } from '../mark.service';
import { MessageService } from '../message.service';
import { Course } from '../course'
import { CourseService } from '../course.service';
import { Category } from '../category';
import { CategoryService } from '../category.service';
import { GlobalGradeComponent } from '../global-grade/global-grade.component';
import { MessageObject } from '../message-object';


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
  categories: Category[];
  //Indicates whether the changes were saved or not.
  saved: boolean;

  constructor(
    private markService: MarkService, 
    private messageService: MessageService, 
    private courseService: CourseService, 
    private categoryService: CategoryService) {}

  ngOnInit(): void {
    this.saved = true;
    this.marks = [];
    this.courseService.getCourses().subscribe(courses => {
      this.courses = courses;
      this.selectedCourseId = this.courses[0].id;
      this.categoryService.getCategories().subscribe(categories => {
        this.categories = categories;
        this.selectedCourseId = this.courses[0].id;
        this.getMarks(this.selectedCourseId);
      }); 
    }); 
  }

  onSelect(mark: Mark): void {
    this.selectedMark = mark;
    this.saved = false;
    //this.messageService.add(`MarksComponent: Selected mark id=${mark.id}`);
  }

  onChange(courseId: number): void{
    this.saved = true;
    this.selectedMark = null;
    this.getMarks(courseId);
  }

  onCategoryChange(categoryId: number): void{
    this.selectedMark.category = this.categories.find(category => category.id === categoryId);
  }

  unselectMark(): void{
    this.selectedMark = null;
  }

  getMarks(courseId: number): void {
    this.markService.getMarks(courseId).subscribe(marks => this.marks = marks);
  }

  save(marks: Mark[]): void{
    this.markService.updateMarks(marks).subscribe(() => {
      this.saved = true;
      this.messageService.add(new MessageObject('Changes successfully saved', true));
    });
  }

  delete(id: number): void{
    this.markService.deleteMark(id).subscribe(() => {
      const index: number = this.marks.indexOf(this.selectedMark);
      if (index !== -1) {
        this.marks.splice(index, 1);
        this.messageService.add(new MessageObject('Deleted mark ' + this.selectedMark.description, true));
        delete this.selectedMark;
      }  
    });
  }
  

}
