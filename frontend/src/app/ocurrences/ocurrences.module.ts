import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OcurrencesRoutingModule } from './ocurrences-routing.module';
import { CategoryComponent } from './category/category.component';
import { NewOccurenceComponent } from './new-occurrence/new-occurrence.component';
import { ReactiveFormsModule } from '@angular/forms';
//import { FormBuilder } from '@angular/forms';


@NgModule({
  declarations: [
    CategoryComponent,
    NewOccurenceComponent
  ],
  imports: [
    CommonModule,
    OcurrencesRoutingModule,
    ReactiveFormsModule
  ]
})
export class OcurrencesModule { }
