import { Component, OnInit } from '@angular/core';

import { Grade } from '../grade'
import { GradeService } from '../grade.service';
import { MessageService } from '../message.service';
import { SEMESTERS } from '../mock-semesters';

@Component({
  selector: 'app-grades',
  templateUrl: './grades.component.html',
  styleUrls: ['./grades.component.css']
})
export class GradesComponent implements OnInit {

  grades: Grade[];
  semesters: number[] = SEMESTERS;
  selectedSemester: number = 1;

  constructor(private gradeService: GradeService, private messageService: MessageService) {}

  ngOnInit(): void {
    this.getGrades(1);
  }

  // getGrades(): void {
  //   this.gradeService.getGrades().subscribe(grades => this.grades = grades);
  // }

  getGrades(semester: number): void {
    this.gradeService.getGrades(semester).subscribe(grades => this.grades = grades);
  }

  onChange(semester: number): void{
    this.getGrades(semester);
  }

}
