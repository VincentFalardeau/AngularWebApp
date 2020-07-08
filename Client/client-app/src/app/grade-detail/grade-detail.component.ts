import { Component, OnInit, Input } from '@angular/core';

import { Grade } from '../grade';

@Component({
  selector: 'app-grade-detail',
  templateUrl: './grade-detail.component.html',
  styleUrls: ['./grade-detail.component.css']
})
export class GradeDetailComponent implements OnInit {

  @Input() grade: Grade;

  constructor() { }

  ngOnInit(): void {
  }

}
