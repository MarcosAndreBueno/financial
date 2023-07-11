import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NewOccurrenceComponent } from './new-occurrence/new-occurrence.component';
import { OccurrenceResolver } from './guards/occurrence.resolver';
import { OccurrencesComponent } from './occurrences.component';
import { OccurrenceService } from './services/occurrence.service';
import { IncomeService } from './services/impl/income.service';

const routes: Routes = [
  { path: '', component: OccurrencesComponent },
  {
    path: 'new-occurrence', component: NewOccurrenceComponent,
    resolve: { occurrence: OccurrenceResolver }
  },
  {
    path: 'update/:id', component: NewOccurrenceComponent,
    resolve: { occurrence: OccurrenceResolver }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class OccurrencesRoutingModule { }
