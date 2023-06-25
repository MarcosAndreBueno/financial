import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoryComponent } from './category/category.component';
import { NewOccurenceComponent } from './new-occurrence/new-occurrence.component';

const routes: Routes = [
  { path: '', component: CategoryComponent }, // /occurrences
  { path: 'new-occurrence', component: NewOccurenceComponent} // /occurrences/new-occurence
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OcurrencesRoutingModule { }
