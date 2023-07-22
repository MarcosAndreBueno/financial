import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OccurrencesRoutingModule } from './occurrences-routing.module';
import { OccurrencesComponent } from './occurrences.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { NewOccurrenceComponent } from './new-occurrence/new-occurrence.component';
import { NewCategoryComponent } from './new-category/new-category.component';
import { NewTypeComponent } from './new-type/new-type.component';


@NgModule({
  declarations: [
    OccurrencesComponent,
    NewOccurrenceComponent,
    NewCategoryComponent,
    NewTypeComponent
  ],
  imports: [
    CommonModule,
    OccurrencesRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    BsDatepickerModule,
  ]
})
export class OccurrencesModule { }
