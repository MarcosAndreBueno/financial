import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OcurrencesRoutingModule } from './ocurrences-routing.module';
import { CategoryComponent } from './category/category.component';
import { NewOccurenceComponent } from './new-occurrence/new-occurrence.component';


@NgModule({
  declarations: [
    CategoryComponent,
    NewOccurenceComponent
  ],
  imports: [
    CommonModule,
    OcurrencesRoutingModule
  ]
})
export class OcurrencesModule { }
