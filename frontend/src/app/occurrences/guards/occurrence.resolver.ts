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
    //new income
    return of({
      id: '', amount: '', date: '', account: '', type: { id: '', type: '' },
      category: { id: '', category: '' }, description: ''
    });
  }
}