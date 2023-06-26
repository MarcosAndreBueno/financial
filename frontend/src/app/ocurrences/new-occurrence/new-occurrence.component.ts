import { Component } from '@angular/core';
//import { FormControl } from '@angular/forms';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-new-occurrence',
  templateUrl: './new-occurrence.component.html',
  styleUrls: ['./new-occurrence.component.css']
})
export class NewOccurenceComponent {

  constructor(private formBuilder: FormBuilder) {

  }

  //listen form input
  occurrencyForm = this.formBuilder.group({
    _id: [null],
    value: [null],
    data: [null],
    type: [null],
    category: [null],
    description: [null],
  });

  saveOcurrency() {
    console.log('debug occurency: ', this.occurrencyForm.value);
  }
}
