import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MarksComponent } from './marks/marks.component';
import { GradesComponent } from './grades/grades.component';
import { AddMarkComponent } from './add-mark/add-mark.component';

const routes: Routes = [
  { path: '', redirectTo: 'grades', pathMatch: 'full' },
  { path: 'marks', component: MarksComponent },
  { path: 'grades', component: GradesComponent },
  { path: 'add-mark', component: AddMarkComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
