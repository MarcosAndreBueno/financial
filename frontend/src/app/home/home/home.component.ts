import { Observable } from 'rxjs';
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
  public promisse!: Promise<string | void>
  public accountIncomes!: string[]
  public accountOutcomes!: string[]

  constructor(
    private router: Router,
    private currentRoute: ActivatedRoute,
    private accountService: AccountService,
  ) {
    this.accounts$ = accountService.list();
    this.getAccountsTotalIncomes();
    this.getAccountsTotalOutcomes();
  }

  goToIncomes() {
    this.router.navigate(['occurrences/incomes'])
  }

  goToOutcomes() {
    this.router.navigate(['occurrences/outcomes'])
  }

  onAdd() {
    this.router.navigate(['new-account', { relativeTo: this.currentRoute }])
  }

  onEdit(account: Account) {
    this.router.navigate(['update-account', account.id])
  }

  getAccountsTotalIncomes() {
    this.promisse = this.accountService.findAccountsIncomes(this.accounts$)
      .then(
        result => {
          this.accountIncomes = result;
        }
      )
  }

  getAccountsTotalOutcomes() {
    this.promisse = this.accountService.findAccountsOutcomes(this.accounts$)
      .then(
        result => {
          this.accountOutcomes = result;
        }
      )
  }

}
