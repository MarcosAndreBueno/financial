import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IncomesComponent } from './incomes.component';
import { NewIncomeComponent } from '../new-income/new-income.component';

const routes: Routes = [
  {
    path: '', component: IncomesComponent, children: [
      { path: 'new-income', component: NewIncomeComponent },
      { path: 'update/:id', component: NewIncomeComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class IncomesRoutingModule { }
