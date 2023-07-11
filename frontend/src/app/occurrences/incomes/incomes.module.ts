import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { IncomesRoutingModule } from './incomes-routing.module';
import { IncomesComponent } from './incomes.component';
import { NewOccurrenceComponent } from '../new-occurrence/new-occurrence.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [    
    IncomesComponent,
    NewOccurrenceComponent],
  imports: [
    CommonModule,
    IncomesRoutingModule,
    ReactiveFormsModule,
    BsDatepickerModule,
    FormsModule
  ]
})
export class IncomesModule { }
