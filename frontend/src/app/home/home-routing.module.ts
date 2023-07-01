import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { IncomesComponent } from '../occurrences/incomes/incomes.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  {
    path: 'occurrences/incomes',
    component: IncomesComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
