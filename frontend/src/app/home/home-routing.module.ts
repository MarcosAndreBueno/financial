import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';

import { OccurrenceService } from '../occurrences/services/occurrence.service';
import { IncomeService } from '../occurrences/services/impl/income.service';
import { OutcomeService } from '../occurrences/services/impl/outcome.service';
import { AccountResolver } from './home/guards/account.resolver';
import { NewAccountComponent } from './new-account/new-account.component';

const routes: Routes = [
  { path: '', component: HomeComponent},
  {
    path: 'new-account', component: NewAccountComponent,
    resolve: { account: AccountResolver }
  },
  {
    path: 'update-account/:name', component: NewAccountComponent,
    resolve: { account: AccountResolver }
  },
  {
    path: 'occurrences/incomes',
    loadChildren: () => import('../occurrences/occurrences.module').then(m => m.OccurrencesModule),
    providers: [
      { provide: OccurrenceService, useClass: IncomeService }
    ]
  },
  {
    path: 'occurrences/outcomes',
    loadChildren: () => import('../occurrences/occurrences.module').then(m => m.OccurrencesModule),
    providers: [
      { provide: OccurrenceService, useClass: OutcomeService }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
