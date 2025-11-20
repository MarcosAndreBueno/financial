import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';

import { Occurrence } from '../model/occurrence';
import { OccurrenceFactory } from '../services/occurrence.provider';

@Injectable({
  providedIn: 'root'
})
export class OccurrenceResolver implements Resolve<Occurrence> {

  constructor(private factory: OccurrenceFactory) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Occurrence> {
    //update income
    if (route.params && route.params['id']) {
      const occurrenceService = this.factory.getInstanceOf(route.pathFromRoot[1].routeConfig?.path);
      return occurrenceService.loadById(route.params['id']);
    }

    var isIncome = state.url.includes("incomes");
    
    //new income,
    const todayDate = 
      new Date().getFullYear().toString() + '-' +
      (new Date().getMonth() + 1).toString().padStart(2, '0') + '-' +
      new Date().getDate().toString().padStart(2, '0');
    return of({
      id: '', amount: '', date: todayDate,
      account: { id: '', name: '', status_active: true },
      type: { id: '', name: '', income: isIncome, status_active: true },
      category: { id: '', name: '', income: isIncome, status_active: true },
      description: ''
    });
  }
}