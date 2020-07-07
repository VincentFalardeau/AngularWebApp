import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here
import { HttpClientModule }    from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MarksComponent } from './marks/marks.component';
import { MarkDetailsComponent } from './mark-detail/mark-detail.component';
import { MessagesComponent } from './messages/messages.component';
import { GlobalGradeComponent } from './global-grade/global-grade.component';

@NgModule({
  declarations: [
    AppComponent, 
    MarksComponent,
    MarkDetailsComponent,
    MessagesComponent,
    GlobalGradeComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
