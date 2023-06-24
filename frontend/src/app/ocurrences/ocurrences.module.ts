import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OcurrencesRoutingModule } from './ocurrences-routing.module';
import { CategoryComponent } from './category/category.component';


@NgModule({
  declarations: [
    CategoryComponent
  ],
  imports: [
    CommonModule,
    OcurrencesRoutingModule
  ]
})
export class OcurrencesModule { }
