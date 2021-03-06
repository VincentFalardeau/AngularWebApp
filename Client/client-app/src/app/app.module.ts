import { BrowserModule } from '@angular/platform-browser';
import { NgModule, EventEmitter } from '@angular/core';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here
import { HttpClientModule }    from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MarksComponent } from './marks/marks.component';
import { MessagesComponent } from './messages/messages.component';
import { GlobalGradeComponent } from './global-grade/global-grade.component';
import { GradesComponent } from './grades/grades.component';
import { GradeDetailComponent } from './grade-detail/grade-detail.component';
import { AddMarkComponent } from './add-mark/add-mark.component';
import { CourseDetailComponent } from './course-detail/course-detail.component';

import { EventEmitterService } from './event-emitter.service';

@NgModule({
  declarations: [
    AppComponent, 
    MarksComponent,
    MessagesComponent,
    GlobalGradeComponent,
    GradesComponent,
    GradeDetailComponent,
    AddMarkComponent,
    CourseDetailComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [EventEmitterService],
  bootstrap: [AppComponent]
})
export class AppModule { }
