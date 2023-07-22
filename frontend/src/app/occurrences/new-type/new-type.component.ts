import { TypeService } from '../services/type.service';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Type } from '../model/type';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-new-type',
  templateUrl: './new-type.component.html',
  styleUrls: ['./new-type.component.css']
})
export class NewTypeComponent implements OnInit {

  @Output() newType = new EventEmitter();

  typeForm!: FormGroup;
  types$!: Observable<Type[]>;
  selectedType = ''

  constructor(
    private formBuilder: FormBuilder,
    private currentRoute: ActivatedRoute,
    private typeService: TypeService,
    private location: Location
  ) {
    this.types$ = this.typeService.list()
  }

  ngOnInit(): void {
    this.typeForm = this.formBuilder.group({
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
    this.typeForm.patchValue({ id: '' })
    this.typeService.onSave(this.typeForm.value)
      .subscribe(
        () => {
          this.return()
        }
      );
  }

  onEdit() {
    this.typeService.onEdit(this.typeForm.value, this.typeForm.value.id)
      .subscribe(
        () => {
          this.typeForm.patchValue(this.typeForm.value);
          this.return()
        }
      );
  }

  onDelete() {
    this.typeService.deleteById(this.typeForm.value.id).subscribe(
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
    return this.typeForm.value.id === 'Add new'
  }

  get showNameEditor() {
    return this.typeForm.value.id && this.typeForm.value.id !== 'Add new'
  }
}
