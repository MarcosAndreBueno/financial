import { Type } from '../model/type';
import { TypeService } from '../services/type.service';
import { CategoryService } from '../services/category.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router, RouterStateSnapshot } from '@angular/router';
import { Occurrence } from '../model/occurrence';
import { Category } from '../model/category';
import { Observable, filter, map } from 'rxjs';
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
  categoryID!: number;
  isIncomePage!: boolean;

  constructor(
    private formBuilder: FormBuilder,
    private occurrenceService: OccurrenceService,
    private currentRoute: ActivatedRoute,
    private location: Location,
    private categoryService: CategoryService,
    private typeService: TypeService,
    private accountService: AccountService,
    private router: Router
  ) {
    this.categories$ = categoryService.list().pipe(
      map(categories =>
        categories.sort((a, b) => a.name.localeCompare(b.name))
      ));
    this.types$ = typeService.list().pipe(
      map(types =>
        types.sort((a, b) => a.name.localeCompare(b.name))
      ))
    this.accounts$ = accountService.list()
  }

  ngOnInit(): void {
    this.isIncomePage = this.location.path().includes("incomes");
    this.types$.subscribe()
    this.categories$.subscribe()

    //listen form input
    this.occurrenceForm = this.formBuilder.group({
      id: [null],
      amount: [null],
      date: [null],
      account: this.formBuilder.group({
        name: [null],
        status_active: [null]
      }),
      type: this.formBuilder.group({
        id: [null],
        name: [null],
        income: [null],
        status_active: [null]
      }),
      category: this.formBuilder.group({
        id: [null],
        name: [null],
        income: [null],
        status_active: [null]
      }),
      description: [null],
    });

    //get informações carregadas pelo occurrenceResolver (/update/1)
    const occurrence: Occurrence = this.currentRoute.snapshot.data['occurrence']

    this.occurrenceForm.patchValue({
      _id: occurrence.id,
      amount: occurrence.amount,
      date: occurrence.date,
      account: {
        name: occurrence.account.name,
        status_active: occurrence.account.status_active
      },
      type: {
        id: occurrence.type.id,
        name: occurrence.type.name,
        isIncome: occurrence.type.income,
        status_active: occurrence.type.status_active
      },
      category: {
        id: occurrence.category.id,
        name: occurrence.category.name,
        isIncome: occurrence.category.income,
        status_active: occurrence.category.status_active
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
    if (confirm('Are you sure you want to update?'))
      this.occurrenceService.onEdit(this.occurrenceForm.value, this.currentRoute.snapshot.data['occurrence'].id)
        .subscribe(
          () => {
            alert("Occurrence updated")
            this.occurrenceForm.patchValue(this.occurrenceForm.value);
            this.return()
          }
        );
  }

  return() {
    this.location.back();
  }

  categorySettings() {
    this.router.navigate(['new-category'], { relativeTo: this.currentRoute })
  }

  typeSettings() {
    this.router.navigate(['new-type'], { relativeTo: this.currentRoute })
  }
}