import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';

import { Occurrence } from '../model/occurrence';
import { IncomeService } from '../services/income.service';

@Injectable({
  providedIn: 'root'
})
export class IncomeResolver implements Resolve<Occurrence> {

  constructor(private incomeService: IncomeService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Occurrence> {
    //update income
    if (route.params && route.params['id']) {
      return this.incomeService.loadById(route.params['id']);
    }
    //new income
    return of({
      id: '', amount: '', date: '', type: { id: '', type: '' },
      category: { id: '', category: '' }, description: ''
    });
  }
}