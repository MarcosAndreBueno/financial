import { Observable, toArray } from 'rxjs';
import { AccountService } from './../service/account.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Account } from '../model/account';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public accounts$!: Observable<Account[]>
  public totalBalance$!: Observable<number[]>
  public totalBalance!: number[]

  public promisse!: Promise<string | void>


  constructor(
    private router: Router,
    private currentRoute: ActivatedRoute,
    private accountService: AccountService,
  ) {
  }

  ngOnInit(): void {
    //get accounts list
    this.accounts$ = this.accountService.list();
    
    //getTotalBalance
    this.totalBalance$ = this.accountService.getTotalBalance(this.accounts$)
    this.totalBalance$.subscribe(result => { this.totalBalance = result as number[] })
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
}
