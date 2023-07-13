import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, concatMap, forkJoin, lastValueFrom, map, switchMap, tap, toArray } from 'rxjs';
import { Account } from '../model/account';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private readonly API = '/api';

  constructor(private httpClient: HttpClient) {}

  list() {
    return this.httpClient.get<Account[]>(`${this.API}/account`)
  }

  onEdit(editedAccount: Partial<Account>, id: number) {
    return this.httpClient.put<Account>(`${this.API}/account/${id}`, editedAccount);
  }
  
  deleteById(id: number) {
    return this.httpClient.delete<Account>(`${this.API}/account/${id}`);
  }

  onSave(newAccount: Account) {
    return this.httpClient.post<Account>(`${this.API}/account`, newAccount);
  }

  loadById(id: number) {
    return this.httpClient.get<Account>(`${this.API}/account/${id}`)
  }

  async findAccountsIncomes(accounts$: Observable<Account[]>) {
    //concatmap para lidar com assincronia
    const arrayFinal: string[] = await lastValueFrom(accounts$.pipe(
      concatMap(accounts => accounts.map(account => account.id)),
      concatMap(id => this.httpClient.get<string>(`${this.API}/income/account/${id}`)),
      toArray())
    )
    return arrayFinal;
  }
  
  async findAccountsOutcomes(accounts$: Observable<Account[]>) {
    //concatmap para lidar com assincronia
    const arrayFinal: string[] = await lastValueFrom(accounts$.pipe(
      concatMap(accounts => accounts.map(account => account.id)),
      concatMap(id => this.httpClient.get<string>(`${this.API}/outcome/account/${id}`)),
      toArray())
    )
    return arrayFinal;
  }
}