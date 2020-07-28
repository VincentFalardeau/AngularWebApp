import { Component, OnInit } from '@angular/core';

import { Grade } from '../grade'
import { GradeService } from '../grade.service';
import { SEMESTERS } from '../mock-semesters';

@Component({
  selector: 'app-grades',
  templateUrl: './grades.component.html',
  styleUrls: ['./grades.component.css']
})
export class GradesComponent implements OnInit {

  grades: Grade[];
  selectedGrade: Grade;
  semesters: number[] = SEMESTERS;
  selectedSemester: number = 1;

  constructor(private gradeService: GradeService) {}

  ngOnInit(): void {
    this.getGrades(this.selectedSemester);
  }

  getGrades(semester: number): void {
    this.gradeService.getGrades(semester).subscribe(grades => this.grades = grades);
  }

  //On semester change, the selected semester's grades.
  onChange(semester: number): void{
    this.getGrades(semester);

    this.selectedGrade = null;
  }

  //On grade select, assign the selected grade to the clicked grade.
  onSelect(grade: Grade): void{
    this.selectedGrade = grade;
  }

  //Unselect the currently selected grade.
  unselectGrade(): void{
    this.selectedGrade = null;
  }

}
