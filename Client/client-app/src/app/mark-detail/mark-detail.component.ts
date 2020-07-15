import { Component, OnInit, Input } from '@angular/core';

import { Mark } from '../mark';
import { Course } from '../course'
import { Category } from '../category'
import { MarkService } from '../mark.service';
import { MessageService } from '../message.service';
import { CourseService } from '../course.service';
import { CategoryService } from '../category.service';


@Component({
  selector: 'app-mark-detail',
  templateUrl: './mark-detail.component.html',
  styleUrls: ['./mark-detail.component.css']
})
export class MarkDetailsComponent implements OnInit {

  @Input() mark: Mark;
  
  courses: Course[];
  categories: Category[];

  selectedCourseId: number;

  constructor(
    private markService: MarkService, 
    private messageService: MessageService, 
    private courseService: CourseService, 
    private categoryService: CategoryService) {}

  
  ngOnInit(): void {

    this.categoryService.getCategories().subscribe(categories => {
      this.categories = categories;
      //TODO: Set selected category to the mark's category.
    });

  }
}
