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
    { category: 'EXAMPLE' },
    { category: 'SALARY' },
    { category: 'ANOTHER_EXAMPLE' }
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
      date: [null],
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

    //get informações carregadas pelo incomeResolver (/update/1)
    const income: Occurrence = this.currentRoute.snapshot.data['income']
    console.log(income)
    //popular form
    this.incomeForm.patchValue({
      _id: income.id,
      amount: income.amount,
      date: income.date,
      type: income.type,
      category: {
        category: income.category,
      },
      description: income.description
    })

    console.log("========")
    console.log("========")
    console.log("========")
    console.log("========")
    console.log("========")

    console.log('informação vindo de income: ', this.currentRoute.snapshot.data['income'])
    console.log('formBuilder criado com info: ', this.incomeForm)

    console.log("========")
    console.log("========")
    console.log("========")
    console.log("========")
    console.log("========")
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
  }
}