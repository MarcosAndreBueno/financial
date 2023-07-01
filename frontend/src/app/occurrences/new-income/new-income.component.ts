import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { IncomeService } from '../services/income.service';

@Component({
  selector: 'app-new-income',
  templateUrl: './new-income.component.html',
  styleUrls: ['./new-income.component.css']
})
export class NewIncomeComponent implements OnInit{

  incomeForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private incomeService: IncomeService
    ) {
  }

  ngOnInit(): void {
     //listen form input
    this.incomeForm = this.formBuilder.group({
    _id: [null],
    amount: [null],
    _date: [null],
    type: [null],
    category: [null],
    description: [null],
  });   
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
}
