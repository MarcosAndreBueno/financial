import { Type } from '../model/type';
import { TypeService } from '../services/type.service';
import { CategoryService } from '../services/category.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Occurrence } from '../model/occurrence';
import { Category } from '../model/category';
import { Observable } from 'rxjs';
import { Location } from '@angular/common';
import { OccurrenceService } from '../services/occurrence.service';

@Component({
  selector: 'app-new-occurrence',
  templateUrl: './new-occurrence.component.html',
  styleUrls: ['./new-occurrence.component.css']
})
export class NewOccurrenceComponent implements OnInit {

  occurrenceForm!: FormGroup;
  categories$: Observable<Category[]>;
  types$: Observable<Type[]>;

  constructor(
    private formBuilder: FormBuilder,
    private occurrenceService: OccurrenceService,
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
    this.occurrenceForm = this.formBuilder.group({
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

    //get informações carregadas pelo occurrenceResolver (/update/1)
    const occurrence: Occurrence = this.currentRoute.snapshot.data['occurrence']
    //popular form
    this.occurrenceForm.patchValue({
      _id: occurrence.id,
      amount: occurrence.amount,
      date: occurrence.date,
      type: {
        type: occurrence.type.type
      },
      category: {
        category: occurrence.category.category,
      },
      description: occurrence.description
    })
  }

  onSubmit() {
    if (this.currentRoute.snapshot.data['occurrence'].id)
      this.onEdit()
    else
      this.onAdd()
  }

  onAdd() {
    this.occurrenceService.onSave(this.occurrenceForm.value)
      .subscribe(
        () => {
          this.return()
        }
      );
  }

  onEdit() {
    this.occurrenceService.onEdit(this.occurrenceForm.value, this.currentRoute.snapshot.data['occurrence'].id)
      .subscribe(
        () => {
          this.occurrenceForm.patchValue(this.occurrenceForm.value);
          this.return()
        }
      );
  }

  return() {
    this.location.back();
  }
}