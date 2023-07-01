import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IncomesComponent } from './incomes/incomes.component';
import { NewIncomeComponent } from './new-income/new-income.component';

const routes: Routes = [
  {
    path: 'occurrences/incomes', component: IncomesComponent,
    children: [{ path: 'new-income', component: NewIncomeComponent }]
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class OccurrencesRoutingModule { }
