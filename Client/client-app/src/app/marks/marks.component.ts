import { Component, OnInit } from '@angular/core';

import { Mark } from '../mark'
import { MarkService } from '../mark.service';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-marks',
  templateUrl: './marks.component.html',
  styleUrls: ['./marks.component.css']
})
export class MarksComponent implements OnInit {

  marks: Mark[];
  selectedMark: Mark;

  onSelect(mark: Mark): void {
    this.selectedMark = mark;
    this.messageService.add(`MarksComponent: Selected mark id=${mark.id}`);
  }

  constructor(private markService: MarkService, private messageService: MessageService) {}

  ngOnInit(): void {
    this.getMarks();
  }

  getMarks(): void {
    this.markService.getMarks().subscribe(marks => this.marks = marks);
  }

  

}
