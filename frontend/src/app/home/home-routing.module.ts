import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { IncomesComponent } from '../occurrences/incomes/incomes.component';
import { NewIncomeComponent } from '../occurrences/new-income/new-income.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'incomes', component: IncomesComponent,
  children: [{ path: 'new-income', component: NewIncomeComponent }]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
