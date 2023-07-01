import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OccurrencesRoutingModule } from './occurrences-routing.module';
import { ReactiveFormsModule } from '@angular/forms';
import { NewIncomeComponent } from './new-income/new-income.component';
import { IncomesComponent } from './incomes/incomes.component';


@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule,
    OccurrencesRoutingModule
  ]
})
export class OccurrencesModule { }
