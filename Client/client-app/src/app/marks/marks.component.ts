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
  selectedMark: Mark;
  courses: Course[];
  selectedCourseId: number;
  categories: Category[];

  constructor(
    private markService: MarkService, 
    private messageService: MessageService, 
    private courseService: CourseService, 
    private categoryService: CategoryService,
    private eventEmitterService: EventEmitterService,
    private markValidationService: MarkValidationService) {}

  ngOnInit(): void {

    this.selectedMarkId = null;
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

  //Adds a mark
  addMark(): void{

    //Basic mark
    let mark = new MarkObject(0, "description", 85, 10);
    mark.category = this.categories[0];
    //Its course is the selected course
    mark.course = this.courses.find(course => course.id == this.selectedCourseId);
    
    //Add the mark in the database
    this.markService.addMark(mark).subscribe(()=>{
      this.getMarks(this.selectedCourseId);           
      this.refreshGlobalGrade();
    });

  }

  //Refreshes the global grade displayed in the global grade component.
  refreshGlobalGrade(){    
    this.eventEmitterService.onGetGlobalGrade();    
  } 

  //On mark select, takes the selected mark as parameter.
  onSelect(mark: Mark): void {
    this.selectedMarkId = mark.id;
    //selectedMark is a copy of the mark object, so that changes are not immediately commited
    this.selectedMark = Object.assign({}, mark);
  }

  //On course change, takes the selected course's id as a number.
  onChange(courseId: number): void{
    this.selectedMarkId = null;
    this.getMarks(courseId);
  }

  //Associates the category id to the corresponding category object
  onCategoryChange(categoryId: number): void{
    this.selectedMark.category = this.categories.find(category => category.id === categoryId);
  }

  //Changes the values of the original copy of the selected mark.
  commitChanges(mark: Mark): void{
    
    //Assign new values to the mark so that the mark variable's reference remain the original
    mark.description = this.selectedMark.description;
    mark.mark = this.selectedMark.mark;
    mark.weight = this.selectedMark.weight;
    mark.category = this.categories.find(category => category.id === this.selectedMark.category.id);

    //Update the mark in the database.
    this.markService.updateMark(mark).subscribe(()=> this.selectedMarkId = null);
  }

  //Cancel the selected mark's editing. 
  cancelEdit(): void{
    this.selectedMarkId = null;
  }

  //Fetches the marks of the specified course from the database.
  getMarks(courseId: number): void {
    this.markService.getMarks(courseId).subscribe(marks => this.marks = marks);
  }

  //Deletes the mark having the specified id in the database
  delete(id: number): void{

    //Get the index of the specified mark.
    let index: number = -1;
    for(let i = 0; i < this.marks.length; i++){
      if(this.marks[i].id == id){
        index = i;
        break;
      }
    }
    //Get the specified mark.
    let mark = this.marks[index];

    this.markService.deleteMark(mark).subscribe(() => {

      //If the mark is found in the array, delete it
      if (index !== -1) {

        this.marks.splice(index, 1);

        this.selectedMarkId = null;
        this.refreshGlobalGrade();
      }  

    });
  }
  

}
