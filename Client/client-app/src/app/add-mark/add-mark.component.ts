import { Component, OnInit } from '@angular/core';

import { Mark } from '../mark';
import { MarkObject } from '../mark-object';
import { Course } from '../course'
import { Category } from '../category'
import { MarkService } from '../mark.service';
import { MessageService } from '../message.service';
import { CourseService } from '../course.service';
import { CategoryService } from '../category.service';
import { MessageObject } from '../message-object';
import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { EventEmitterService } from '../event-emitter.service';   
import { MarkValidationService } from '../mark-validation.service';  

@Component({
  selector: 'app-add-mark',
  templateUrl: './add-mark.component.html',
  styleUrls: ['./add-mark.component.css']
})
export class AddMarkComponent implements OnInit {

  mark: Mark;
  
  courses: Course[];
  categories: Category[];

  constructor(
    private markService: MarkService, 
    private messageService: MessageService, 
    private courseService: CourseService, 
    private categoryService: CategoryService,
    private eventEmitterService: EventEmitterService,
    private markValidationService: MarkValidationService) {}

  
  ngOnInit(): void {

    this.mark = new MarkObject(0, "description", 85, 10);

    //Get the categories
    this.categoryService.getCategories().subscribe(categories => {
      this.categories = categories;

      //Select the first category
      this.mark.category = categories[0];

      //Get the courses
      this.courseService.getCourses().subscribe(courses => {
        this.courses = courses;

        //Select the first course
        this.mark.course = courses[0];
        
      });
    });

  }

  //Refreshes the global grade displayed in the global grade component.
  refreshGlobalGrade(){    
    this.eventEmitterService.onGetGlobalGrade();    
  } 

  //Adds the mark in the database
  add(mark: Mark): void{
    this.markService.addMark(mark).subscribe(()=>this.refreshGlobalGrade());
  }

}
