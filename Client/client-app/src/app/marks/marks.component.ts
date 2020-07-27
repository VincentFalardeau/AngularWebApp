import { Component, OnInit } from '@angular/core';

import { Mark } from '../mark'
import { MarkService } from '../mark.service';
import { MessageService } from '../message.service';
import { Course } from '../course'
import { CourseService } from '../course.service';
import { Category } from '../category';
import { CategoryService } from '../category.service';
import { MessageObject } from '../message-object';
import { EventEmitterService } from '../event-emitter.service';   
import { MarkValidationService } from '../mark-validation.service'; 
import { MarkObject } from '../mark-object';


@Component({
  selector: 'app-marks',
  templateUrl: './marks.component.html',
  styleUrls: ['./marks.component.css']
})
export class MarksComponent implements OnInit {

  marks: Mark[];
  selectedMarkId: number;
  //The copy that is edited.
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
    private categoryService: CategoryService,
    private eventEmitterService: EventEmitterService,
    private markValidationService: MarkValidationService) {}

  ngOnInit(): void {

    this.selectedMarkId = null;
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

  addMark(): void{
    let mark = new MarkObject(0, "description", 85, 10);
    mark.category = this.categories[0];
    mark.course = this.courses.find(course => course.id == this.selectedCourseId);
    
    this.markService.addMark(mark).subscribe(()=>{

      this.getMarks(this.selectedCourseId);

      //this.marks.push(mark);

      //Log the success
      this.messageService.add(new MessageObject('Added mark ' + mark.description + ' in course ' + mark.course.description, true));

      this.getGlobalGrade();

    //Log the error
    }, error=> this.messageService.add(new MessageObject(error, false)));

  }

  //Refreshes the global grade displayed in the global grade component.
  getGlobalGrade(){    
    this.eventEmitterService.onFirstComponentButtonClick();    
  } 

  onSelect(mark: Mark): void {
    this.selectedMarkId = mark.id;
    this.selectedMark = Object.assign({}, mark);
    this.saved = false;
  }

  onChange(courseId: number): void{
    this.saved = true;
    this.selectedMarkId = null;
    this.getMarks(courseId);
  }

  onCategoryChange(categoryId: number): void{
    this.selectedMark.category = this.categories.find(category => category.id === categoryId);
  }

  confirmEdit(mark: Mark): void{
    
    mark.description = this.selectedMark.description;
    mark.mark = this.selectedMark.mark;
    mark.weight = this.selectedMark.weight;
    mark.category = this.categories.find(category => category.id === this.selectedMark.category.id);

    this.markService.updateMarks([mark]).subscribe(()=>{
      this.messageService.add(new MessageObject('Mark ' + mark.description + ' successfully updated', true));
      this.getGlobalGrade();
      this.selectedMarkId = null;
    });
  }

  cancelChange(): void{
    this.selectedMarkId = null;
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
      let index: number = -1;

      for(let i = 0; i < this.marks.length; i++){
        if(this.marks[i].id == id){
          index = i;
          break;
        }
      }

      if (index !== -1) {
        this.marks.splice(index, 1);
        this.messageService.add(new MessageObject('Deleted mark ' + this.selectedMark.description, true));
        this.selectedMarkId = null;
        this.getGlobalGrade();
      }  
    });
  }
  

}
