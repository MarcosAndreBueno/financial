import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IncomesComponent } from './incomes.component';
import { NewOccurrenceComponent } from '../new-occurrence/new-occurrence.component';

const routes: Routes = [
  { path: '', component: IncomesComponent },
  { path: 'new-income', component: NewOccurrenceComponent },
  { path: 'update/:id', component: NewOccurrenceComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class IncomesRoutingModule { }
