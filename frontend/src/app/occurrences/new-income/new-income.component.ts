import { Type } from './../model/type';
import { TypeService } from './../services/type.service';
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
  types$: Observable<Type[]>;

  constructor(
    private formBuilder: FormBuilder,
    private incomeService: IncomeService,
    private currentRoute: ActivatedRoute,
    private location: Location,
    private categoryService: CategoryService,
    private typeService: TypeService
  ) {
    this.categories$ = categoryService.list()
    this.types$ = typeService.list()
  }

  ngOnInit(): void {
    //listen form input
    this.incomeForm = this.formBuilder.group({
      id: [null],
      amount: [null],
      date: [null],
      type: this.formBuilder.group({
        type: [null]
      }),
      category: this.formBuilder.group({
        category: [null]
      }),
      description: [null],
    });

    //get informações carregadas pelo incomeResolver (/update/1)
    const income: Occurrence = this.currentRoute.snapshot.data['income']
    //popular form
    this.incomeForm.patchValue({
      _id: income.id,
      amount: income.amount,
      date: income.date,
      type: {
        type: income.type.type
      },
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