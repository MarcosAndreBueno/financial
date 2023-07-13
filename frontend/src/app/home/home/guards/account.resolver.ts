import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { AccountService } from '../../service/account.service';
import { Account } from '../../model/account';

@Injectable({
  providedIn: 'root'
})
export class AccountResolver implements Resolve<Account> {

  constructor(private accountService: AccountService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Account> {    
    //update income
    if (route.params && route.params['id']) {
      return this.accountService.loadById(route.params['id']);
    }
    //new income
    return of({
      id: '', name: '', status_active: true
    });
  }
}