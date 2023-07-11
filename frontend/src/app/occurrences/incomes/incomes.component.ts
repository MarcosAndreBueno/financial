import { Occurrence } from './../model/occurrence';
import { IncomeService } from './../services/income.service';
import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { CategoryService } from '../services/category.service';
import { ActivatedRoute, Router } from '@angular/router';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';

@Component({
  selector: 'app-incomes',
  templateUrl: './incomes.component.html',
  styleUrls: ['./incomes.component.css']
})
export class IncomesComponent {
  incomes$: Observable<Occurrence[]>;

  bsConfig?: Partial<BsDatepickerConfig>;
  selectedDate = new Date();

  constructor(
    private categoryService: CategoryService,
    private incomeService: IncomeService,
    private router: Router,
    private currentRoute: ActivatedRoute,
  ) {
    this.incomes$ = this.incomeService.list(
      this.selectedDate.getMonth() + 1, this.selectedDate.getFullYear()
    );

    this.bsConfig = Object.assign({}, {
      containerClass: 'theme-dark-blue',
      dateInputFormat: 'MM/YYYY'
    });
  }

  newIncome() {
    //relativa à rota atual
    this.router.navigate(['new-income'], { relativeTo: this.currentRoute })
  }

  updateIncome(income: Occurrence) {
    this.router.navigate(['update', income.id], { relativeTo: this.currentRoute })
  }

  deleteIncome(income: Occurrence) {
    this.incomeService.deleteById(income.id).subscribe(
      () => {
        console.log("Exclusão bem sucedida!");
        this.refresh()
      },
      (error) => {
        console.log("Erro ao excluir: ", error);
      })
  }

  refresh() {
    this.incomes$ = this.incomeService.list(
      this.selectedDate.getMonth() + 1, this.selectedDate.getFullYear()
    );
  }

  return() {
    this.router.navigate(['home'])
  }

  //month and year only
  onOpenCalendar(container: any) {
    container.monthSelectHandler = (event: any): void => {
      container._store.dispatch(container._actions.select(event.date));
    };
    container.setViewMode('month');
  }

  increaseMonth() {
    this.selectedDate = new Date(this.selectedDate.getFullYear(),
      this.selectedDate.getMonth() + 1, this.selectedDate.getDate());
    this.refresh()
  }

  decreaseMonth() {
    this.selectedDate = new Date(this.selectedDate.getFullYear(),
      this.selectedDate.getMonth() - 1, this.selectedDate.getDate());
    this.refresh()
  }
}
