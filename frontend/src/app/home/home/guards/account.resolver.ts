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
    console.log('entrou nessa parte do resolver')
    //update income
    if (route.params && route.params['name']) {
      console.log('entrou nessa parte deep do resolver')
      return this.accountService.loadByName(route.params['name']);
    }
    //new income
    return of({
      id: '', name: '', status_active: true
    });
  }
}