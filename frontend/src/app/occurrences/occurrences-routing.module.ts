import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NewOccurrenceComponent } from './new-occurrence/new-occurrence.component';
import { OccurrenceResolver } from './guards/occurrence.resolver';
import { OccurrencesComponent } from './occurrences.component';
import { NewCategoryComponent } from './new-category/new-category.component';
import { NewTypeComponent } from './new-type/new-type.component';

const routes: Routes = [
  { path: '', component: OccurrencesComponent },
  {
    path: 'new-occurrence',
    component: NewOccurrenceComponent,
    resolve: { occurrence: OccurrenceResolver }
  },
  {
    path: 'update/:id',
    component: NewOccurrenceComponent,
    resolve: { occurrence: OccurrenceResolver }
  },
  { path: 'update/:id/new-category', redirectTo: 'new-category' },
  { path: 'new-occurrence/new-category', redirectTo: 'new-category' },
  { path: 'update/:id/new-type', redirectTo: 'new-type' },
  { path: 'new-occurrence/new-type', redirectTo: 'new-type' },
  {
    path: 'new-category',
    component: NewCategoryComponent,
  },
  {
    path: 'new-type',
    component: NewTypeComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class OccurrencesRoutingModule { }
