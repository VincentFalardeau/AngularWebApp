import { Component, OnInit } from '@angular/core';

import { MessageService } from '../message.service';
import { GlobalGrade } from '../global-grade'
import { GlobalGradeService } from '../global-grade.service'
import { EventEmitterService } from '../event-emitter.service';

@Component({
  selector: 'app-global-grade',
  templateUrl: './global-grade.component.html',
  styleUrls: ['./global-grade.component.css']
})
export class GlobalGradeComponent implements OnInit {

  globalGrade: GlobalGrade;

  constructor(
    private globalGradeService: GlobalGradeService, 
    private messageService: MessageService,
    private eventEmitterService: EventEmitterService) { }

  ngOnInit(): void {
    this.getGlobalGrade();
    if (this.eventEmitterService.subsVar==undefined) {    
      this.eventEmitterService.subsVar = this.eventEmitterService.    
      invokeFirstComponentFunction.subscribe((name:string) => {    
        this.getGlobalGrade();    
      });    
    }  
  }

  getGlobalGrade(): void {
    this.globalGradeService.getGlobalGrade().subscribe(globalGrade => this.globalGrade = globalGrade);
  }

  

}
