import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MarksComponent } from './marks/marks.component';

const routes: Routes = [
  { path: '', redirectTo: '/marks', pathMatch: 'full' },
  { path: 'marks', component: MarksComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
