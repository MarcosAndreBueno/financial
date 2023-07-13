import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home/home.component';
import { OccurrencesModule } from '../occurrences/occurrences.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NewAccountComponent } from './new-account/new-account.component';


@NgModule({
  declarations: [
    HomeComponent,
    NewAccountComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    OccurrencesModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class HomeModule { }
