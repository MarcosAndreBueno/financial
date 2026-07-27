import { map, Observable, toArray } from 'rxjs';
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
  public totalBalance!: number

  public promisse!: Promise<string | void>


  constructor(
    private router: Router,
    private currentRoute: ActivatedRoute,
    private accountService: AccountService,
  ) {
  }

  ngOnInit(): void {
    //get accounts list
    this.accounts$ = this.accountService.list().pipe(
      map(accounts => accounts.sort((a, b) => a.name.localeCompare(b.name)))
    );
    
    this.accounts$.subscribe(accounts => {
      this.totalBalance = accounts.reduce((sum, account) => sum + account.amount, 0);
    });
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

  onAnalysis() {
    this.router.navigate(['analysis', { relativeTo: this.currentRoute }])
  }

  onEdit(account: Account) {
    this.router.navigate(['update-account', account.id])
  }
}
