import { CategoryService } from './../services/category.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { IncomeService } from '../services/income.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Occurrence } from '../model/occurrence';
import { Category } from '../model/category';
import { Observable } from 'rxjs';
import { Location } from '@angular/common';

@Component({
  selector: 'app-new-income',
  templateUrl: './new-income.component.html',
  styleUrls: ['./new-income.component.css']
})
export class NewIncomeComponent implements OnInit {

  incomeForm!: FormGroup;
  categories$: Observable<Category[]>;

  constructor(
    private formBuilder: FormBuilder,
    private incomeService: IncomeService,
    private currentRoute: ActivatedRoute,
    private location: Location,
    private categoryService: CategoryService
  ) {
    this.categories$ = categoryService.list()
  }

  ngOnInit(): void {
    //listen form input
    this.incomeForm = this.formBuilder.group({
      id: [null],
      amount: [null],
      date: [null],
      type: [null],
      category: this.formBuilder.group({
        category: [null]
      }),
      description: [null],
    });

    //get informações carregadas pelo incomeResolver (/update/1)
    const income: Occurrence = this.currentRoute.snapshot.data['income']
    console.log(income)
    //popular form
    this.incomeForm.patchValue({
      _id: income.id,
      amount: income.amount,
      date: income.date,
      type: 'INCOME_L',
      category: {
        category: income.category.category,
      },
      description: income.description
    })
  }

  submitButton() {
    if (this.currentRoute.snapshot.data['income'].id)
      this.editOccurrence()
    else
      this.saveOccurrence()
  }

  saveOccurrence() {
    console.log('saveOccurrence')
    this.incomeService.onSave(this.incomeForm.value)
      .subscribe(
        () => {
          this.return()
        }
      );
  }

  editOccurrence() {
    this.incomeService.onEdit(this.incomeForm.value, this.currentRoute.snapshot.data['income'].id)
      .subscribe(
        () => {
          this.incomeForm.patchValue(this.incomeForm.value);
          this.return()
        }
      );
  }

  return() {
    this.location.back();
  }
}