import { Occurrence } from './../model/occurrence';
import { IncomeService } from './../services/income.service';
import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { CategoryService } from '../services/category.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-incomes',
  templateUrl: './incomes.component.html',
  styleUrls: ['./incomes.component.css']
})
export class IncomesComponent {
  incomes$: Observable<Occurrence[]>;
  
  constructor(
    private categoryService: CategoryService, //injeção categoryService
    private incomeService: IncomeService,
    private router: Router, //controler roteamento angular
    private currentRoute: ActivatedRoute //rota atual
    ) { 
    this.incomes$ = this.incomeService.list();
  }

  newIncome() {
    //relativa à rota atual
    this.router.navigate(['new-income'], {relativeTo: this.currentRoute})
  }

  updateIncome(income: Occurrence) {
    this.router.navigate(['update', income.id], {relativeTo: this.currentRoute})
  }
}
