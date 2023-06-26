import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { OccurrenceService } from '../services/occurrence.service';

@Component({
  selector: 'app-new-occurrence',
  templateUrl: './new-occurrence.component.html',
  styleUrls: ['./new-occurrence.component.css']
})
export class NewOccurenceComponent implements OnInit{

  occurrencyForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private occService: OccurrenceService
    ) {
  }

  ngOnInit(): void {
     //listen form input
    this.occurrencyForm = this.formBuilder.group({
    _id: [null],
    amount: [null],
    _date: [null],
    type: [null],
    category: [null],
    description: [null],
  });   
  }

  saveOcurrency() {
    this.occService.onSave(this.occurrencyForm.value)
    .subscribe(
      {
        next: (value) => console.log(value),
        error: (msg) => console.log(msg),
      }
    )
  }
}
