import { CategoryService } from './../services/category.service';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Category } from '../model/category';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-new-type',
  templateUrl: './new-category.component.html',
  styleUrls: ['./new-category.component.css']
})
export class NewCategoryComponent implements OnInit {

  @Output() newCategory = new EventEmitter();

  categoryForm!: FormGroup;
  categories$!: Observable<Category[]>;
  selectedCategory = ''

  constructor(
    private formBuilder: FormBuilder,
    private currentRoute: ActivatedRoute,
    private categoryService: CategoryService,
    private location: Location
  ) {
    this.categories$ = this.categoryService.list()
  }

  ngOnInit(): void {
    this.categoryForm = this.formBuilder.group({
      id: '',
      name: '',
      status_active: true
    })
  }

  onSubmit() {
    if (this.showNameEditor)
      this.onEdit()
    else
      this.onAdd()
  }

  onAdd() {
    this.categoryForm.patchValue({ id: '' })
    this.categoryService.onSave(this.categoryForm.value)
      .subscribe(
        () => {
          this.return()
        }
      );
  }

  onEdit() {
    this.categoryService.onEdit(this.categoryForm.value, this.categoryForm.value.id)
      .subscribe(
        () => {
          this.categoryForm.patchValue(this.categoryForm.value);
          this.return()
        }
      );
  }

  onDelete() {
    this.categoryService.deleteById(this.categoryForm.value.id).subscribe(
      () => {
        console.log("Exclusão bem sucedida!");
        this.return()
      },
      (error) => {
        console.log("Erro ao excluir: ", error);
      })
  }

  return() {
    this.location.back();
  }

  get showNewName() {
    return this.categoryForm.value.id === 'Add new'
  }

  get showNameEditor() {
    return this.categoryForm.value.id && this.categoryForm.value.id !== 'Add new'
  }
}
