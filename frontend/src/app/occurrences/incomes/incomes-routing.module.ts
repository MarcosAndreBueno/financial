import { IncomeResolver } from '../guards/income.resolver';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IncomesComponent } from './incomes.component';
import { NewIncomeComponent } from '../new-income/new-income.component';

const routes: Routes = [
  {
    path: '', component: IncomesComponent, children: [
      { path: 'new-income', component: NewIncomeComponent, resolve: { income: IncomeResolver } },
      { path: 'update/:id', component: NewIncomeComponent, resolve: { income: IncomeResolver } }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class IncomesRoutingModule { }
