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
import { AccountService } from 'src/app/home/service/account.service';
import { Account } from 'src/app/home/model/account';

@Component({
  selector: 'app-new-occurrence',
  templateUrl: './new-occurrence.component.html',
  styleUrls: ['./new-occurrence.component.css']
})
export class NewOccurrenceComponent implements OnInit {

  occurrenceForm!: FormGroup;
  categories$: Observable<Category[]>;
  types$: Observable<Type[]>;
  accounts$: Observable<Account[]>;

  constructor(
    private formBuilder: FormBuilder,
    private occurrenceService: OccurrenceService,
    private currentRoute: ActivatedRoute,
    private location: Location,
    private categoryService: CategoryService,
    private typeService: TypeService,
    private accountService: AccountService
  ) {
    this.categories$ = categoryService.list()
    this.types$ = typeService.list()
    this.accounts$ = accountService.list()
  }

  ngOnInit(): void {
    //listen form input
    this.occurrenceForm = this.formBuilder.group({
      id: [null],
      amount: [null],
      date: [null],
      account: this.formBuilder.group({
        name: [null]
      }),
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
      account: {
        name: occurrence.account.name
      },
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