import { Component, OnInit, Input } from '@angular/core';

import { Mark } from '../mark';


@Component({
  selector: 'app-mark-detail',
  templateUrl: './mark-detail.component.html',
  styleUrls: ['./mark-detail.component.css']
})
export class MarkDetailsComponent implements OnInit {

  @Input() mark: Mark;

  constructor() { }

  ngOnInit(): void {
  }

}
