import { Observable, lastValueFrom, map } from 'rxjs';
import { AccountService } from './../service/account.service';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Account } from '../model/account';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  readonly accounts$: Observable<Account[]>
  private accountIncomes!: Promise<string[]>
  private accountOutcomes!: Promise<string[]>

  constructor(
    private router: Router,
    private currentRoute: ActivatedRoute,
    private accountService: AccountService
  ) {
    this.accounts$ = accountService.list();
  }

  goToIncomes() {
    this.router.navigate(['occurrences/incomes'])
  }

  goToOutcomes() {
    this.router.navigate(['occurrences/outcomes'])
  }

  refresh() {
    this.accountService.list();
    this.accounts$.subscribe(result => { console.log(result)})
  }

  getAccountsTotalIncomes() {
    this.accountIncomes = this.accountService.findAccountsIncomes(this.accounts$)
    console.log(this.accountIncomes)
  }
 
  getAccountsTotalOutcomes() {
    this.accountOutcomes = this.accountService.findAccountsOutcomes(this.accounts$)
    console.log(this.accountOutcomes)
  }
}
