import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IncomesComponent } from './incomes/incomes.component';

const routes: Routes = [
  { path: 'incomes', component: IncomesComponent }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class OccurrencesRoutingModule { }
