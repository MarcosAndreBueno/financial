import { Observable } from 'rxjs';
import { AccountService } from './../service/account.service';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Account } from '../model/account';
import { FormBuilder, FormGroup } from '@angular/forms';

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
    private accountService: AccountService,
  ) {
    this.accounts$ = accountService.list();
    this.accounts$.subscribe(print => { console.log(print)})
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
    this.router.navigate(['update-account', account.name, { relativeTo: this.currentRoute }])
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
