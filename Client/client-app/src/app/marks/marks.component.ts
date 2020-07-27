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
import { EventEmitterService } from '../event-emitter.service';   


@Component({
  selector: 'app-marks',
  templateUrl: './marks.component.html',
  styleUrls: ['./marks.component.css']
})
export class MarksComponent implements OnInit {

  marks: Mark[];
  selectedMark: Mark;
  //The copy that is edited.
  selectedMarkCopy: Mark;

  courses: Course[];
  selectedCourseId: number;

  categories: Category[];

  //Indicates whether the changes were saved or not.
  saved: boolean;

  constructor(
    private markService: MarkService, 
    private messageService: MessageService, 
    private courseService: CourseService, 
    private categoryService: CategoryService,
    private eventEmitterService: EventEmitterService) {}

  ngOnInit(): void {

    this.saved = true;
    this.marks = [];

    //Get the courses
    this.courseService.getCourses().subscribe(courses => {
      this.courses = courses;

      //Get the categories
      this.categoryService.getCategories().subscribe(categories => {
        this.categories = categories;

        //Select the initial course
        this.selectedCourseId = this.courses[0].id;

        //Get the marks for the initial course
        this.getMarks(this.selectedCourseId);
      }); 
    }); 
  }

  //Refreshes the global grade displayed in the global grade component.
  getGlobalGrade(){    
    this.eventEmitterService.onFirstComponentButtonClick();    
  } 

  onSelect(mark: Mark): void {
    this.selectedMark = mark;
    this.selectedMarkCopy = Object.assign({}, this.selectedMark);
    this.saved = false;
  }

  onChange(courseId: number): void{
    this.saved = true;
    this.selectedMark = null;
    this.getMarks(courseId);
  }

  onCategoryChange(categoryId: number): void{
    this.selectedMarkCopy.category = this.categories.find(category => category.id === categoryId);
  }

  confirmEdit(mark: Mark): void{
    mark.description = this.selectedMarkCopy.description;
    mark.mark = this.selectedMarkCopy.mark;
    mark.weight = this.selectedMarkCopy.weight;
    mark.category = this.categories.find(category => category.id === this.selectedMarkCopy.category.id);
    //mark = Object.assign({}, this.markCopy);
    //this.selectedMark = mark;
    this.selectedMark = null;
  }

  cancelChange(): void{
    this.selectedMark = null;
  }

  getMarks(courseId: number): void {
    this.markService.getMarks(courseId).subscribe(marks => this.marks = marks);
  }

  save(marks: Mark[]): void{
    this.markService.updateMarks(marks).subscribe(() => {
      this.saved = true;
      this.messageService.add(new MessageObject('Changes successfully saved', true));
      this.getGlobalGrade();
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
