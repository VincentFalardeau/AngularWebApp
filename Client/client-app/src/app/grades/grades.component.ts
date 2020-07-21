import { Component, OnInit } from '@angular/core';

import { Grade } from '../grade'
import { GradeService } from '../grade.service';
import { MessageService } from '../message.service';
import { SEMESTERS } from '../mock-semesters';

//Component that displays a grade table for a selected semester.
//Details are shown when selecting a grade.

@Component({
  selector: 'app-grades',
  templateUrl: './grades.component.html',
  styleUrls: ['./grades.component.css']
})
export class GradesComponent implements OnInit {

  grades: Grade[];
  selectedGrade: Grade;
  semesters: number[] = SEMESTERS;
  /*
    TODO: User settings to change the default semester.
  */
  selectedSemester: number = 1;

  constructor(
    private gradeService: GradeService, 
    private messageService: MessageService) {}

  ngOnInit(): void {
    this.getGrades(this.selectedSemester);
  }

  //Fetches the grades from the service for the specified semester.
  //Assigns the fetched grades to the grades variable.
  getGrades(semester: number): void {
    this.gradeService.getGrades(semester).subscribe(grades => this.grades = grades);
  }

  //On semester change, fetch the corresponding grades.
  onChange(semester: number): void{
    this.getGrades(semester);

    //The selected grade is resetted.
    this.selectedGrade = null;
  }

  //On grade select, assigne the selected grade to the clicked grade.
  onSelect(grade: Grade): void{
    this.selectedGrade = grade;
    //this.messageService.add(`MarksComponent: Selected grade course's id =${grade.course.id}`);
  }

  //Unselect the currently selected grade.
  unselectGrade(): void{
    this.selectedGrade = null;
  }

}
