import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Account } from '../model/account';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AccountService } from '../service/account.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-new-account',
  templateUrl: './new-account.component.html',
  styleUrls: ['./new-account.component.css']
})
export class NewAccountComponent {

  public accountForm!: FormGroup;

  constructor(
    private router: Router,
    private currentRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private accountService: AccountService,
    private location: Location
  ) {

    this.accountForm = this.formBuilder.group({
      id: null,
      name: null
    })

    const account: Account = this.currentRoute.snapshot.data['account']
    this.accountForm.patchValue({
      id: account.id,
      name: account.name,
    })
  }


  onSubmit() {
    if (this.currentRoute.snapshot.data['account'].id)
      this.onEdit()
    else
      this.onAdd()
  }

  onAdd() {
    this.accountService.onSave(this.accountForm.value)
      .subscribe(
        () => {
          this.return()
        }
      );
  }

  onEdit() {
    if (confirm('Are you sure you want to update? \n' +
      'This action will affect all previous occurrences using this Account'))
      this.accountService.onEdit(this.accountForm.value, this.currentRoute.snapshot.data['account'].id)
        .subscribe(
          () => {
            alert("Account updated")
            this.accountForm.patchValue(this.accountForm.value);
            this.return();
          }
        );
  }

  onDelete(id: number) {
    if (confirm('Are you sure you want to delete?'))
      this.accountService.deleteById(id)
        .subscribe(
          () => {
            alert("Account deleted")
            this.return();
          }
        )
  }

  return() {
    this.location.back();
  }
}
