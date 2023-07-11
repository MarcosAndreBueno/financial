import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OccurrencesRoutingModule } from './occurrences-routing.module';
import { OccurrencesComponent } from './occurrences.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { NewOccurrenceComponent } from './new-occurrence/new-occurrence.component';


@NgModule({
  declarations: [
    OccurrencesComponent,
    NewOccurrenceComponent
  ],
  imports: [
    CommonModule,
    OccurrencesRoutingModule,
    ReactiveFormsModule,
    BsDatepickerModule,
    FormsModule
  ]
})
export class OccurrencesModule { }
