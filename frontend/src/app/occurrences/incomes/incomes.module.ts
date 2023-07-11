import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { IncomesRoutingModule } from './incomes-routing.module';
import { IncomesComponent } from './incomes.component';
import { NewIncomeComponent } from '../new-income/new-income.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [    
    IncomesComponent,
    NewIncomeComponent],
  imports: [
    CommonModule,
    IncomesRoutingModule,
    ReactiveFormsModule,
    BsDatepickerModule,
    FormsModule
  ]
})
export class IncomesModule { }
