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
    console.log('valor em account vindo do snapshot: ', this.currentRoute.snapshot)
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
    this.router.navigate(['new-account', { relativeTo: this.currentRoute }])
    this.accountService.onEdit(this.accountForm.value, 'Account One')
      .subscribe(
        () => {
          this.accountForm.patchValue(this.accountForm.value);
          this.return();
        }
      );
  }

  onDelete(accountName: string) {
    this.accountService.deleteByName(accountName)
    .subscribe(
      () => { 
        this.return();
      }
    )
  }

  return() {
    this.location.back();
  }
}
