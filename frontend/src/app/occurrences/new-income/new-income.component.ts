import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { IncomeService } from '../services/income.service';
import { ActivatedRoute } from '@angular/router';
import { Occurrence } from '../model/occurrence';
import { Category } from '../model/category';

@Component({
  selector: 'app-new-income',
  templateUrl: './new-income.component.html',
  styleUrls: ['./new-income.component.css']
})
export class NewIncomeComponent implements OnInit {

  incomeForm!: FormGroup;
  categories: Category[] = [
    { category: '1' },
    { category: '10' },
    { category: '20' }
  ];

  constructor(
    private formBuilder: FormBuilder,
    private incomeService: IncomeService,
    private currentRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    //listen form input
    this.incomeForm = this.formBuilder.group({
      _id: [null],
      amount: [null],
      _date: [null],
      type: this.formBuilder.group({
        _id: [null],
        type: [null]
      }),
      category: this.formBuilder.group({
        _id: [null],
        category: [null]
      }),
      description: [null],
    });

    //get informações carregadas pelo incomeResolver
    const income: Occurrence = this.currentRoute.snapshot.data['income']
    console.log(income)
    //popular form
    this.incomeForm.patchValue({
      _id: income.id,
      amount: income.amount,
      _date: income.date,
      type: income.type,
      category: income.category,
      description: income.description
    })
  }

  saveOcurrency() {
    this.incomeService.onSave(this.incomeForm.value)
      .subscribe(
        {
          next: (value) => console.log(value),
          error: (msg) => console.log(msg),
        }
      )
  }

  testes() {
    console.log('teste incomeForm: ', this.incomeForm)
    console.log('teste categories: ', this.incomeForm.get('category'))
    console.log('typeof categories: ', typeof this.incomeForm.get('category'))
    console.log('teste category em incomeForm: ', this.incomeForm.controls['category'].value)
    console.log('teste categories: ', this.categories)
    /* category = this.formBuilder.group({
      'categoryForm': this.formBuilder.array([
        this.formBuilder.group({

        })
      ])
    }) */
  }
}