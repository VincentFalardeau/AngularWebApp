import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MarksComponent } from './marks/marks.component';
import { GradesComponent } from './grades/grades.component';

const routes: Routes = [
  { path: '', redirectTo: 'marks', pathMatch: 'full' },
  { path: 'marks', component: MarksComponent },
  { path: 'grades', component: GradesComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
