import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../model/category';
import { CategoryService } from '../services/category.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-incomes',
  templateUrl: './incomes.component.html',
  styleUrls: ['./incomes.component.css']
})
export class IncomesComponent {
  categories$: Observable<Category[]>;

  constructor(
    private categoryService: CategoryService, //injeção categoryService
    private router: Router, //controler roteamento angular
    private currentRoute: ActivatedRoute //rota atual
    ) { 
    this.categories$ = this.categoryService.list();
  }

  newIncome() {
    //relativa à rota atual
    this.router.navigate(['new-income'], {relativeTo: this.currentRoute})
  }
}
