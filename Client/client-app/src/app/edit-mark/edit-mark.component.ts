import { Component, OnInit, Input } from '@angular/core';
import { Mark } from '../mark';
import { Category } from '../category';

@Component({
  selector: 'app-edit-mark',
  templateUrl: './edit-mark.component.html',
  styleUrls: ['./edit-mark.component.css']
})
export class EditMarkComponent implements OnInit {

  @Input() mark: Mark;
  @Input() categories: Category[];

  constructor() { }

  ngOnInit(): void {
  }

}
