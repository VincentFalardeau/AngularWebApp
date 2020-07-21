import { Component, OnInit } from '@angular/core';

import { Mark } from '../mark';
import { MarkObject } from '../mark-object';
import { Course } from '../course'
import { Category } from '../category'
import { MarkService } from '../mark.service';
import { MessageService } from '../message.service';
import { CourseService } from '../course.service';
import { CategoryService } from '../category.service';

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
    private categoryService: CategoryService) {}

  
  ngOnInit(): void {

    this.mark = new MarkObject();
    this.mark.id = 0;
    this.mark.description = "description";
    this.mark.mark = 85;
    this.mark.weight = 10;

    this.categoryService.getCategories().subscribe(categories => {
      this.categories = categories;
      this.mark.category = categories[0];

      this.courseService.getCourses().subscribe(courses => {
        this.courses = courses;
        this.mark.course = courses[0];
        
      });
    });

  }

  add(mark: Mark): void{
    this.markService.addMark(mark).subscribe(()=>{
      this.messageService.add('Added mark ' + mark.description + ' in course ' + mark.course.description);
    });
  }

}
