import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, concatMap, forkJoin, lastValueFrom, map, switchMap, tap, toArray } from 'rxjs';
import { Account } from '../model/account';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private readonly API = '/api';

  constructor(private httpClient: HttpClient) { }

  list() {
    return this.httpClient.get<Account[]>(`${this.API}/account`)
  }

  async findAccountsIncomes(accounts$: Observable<Account[]>): Promise<string[]> {
    //concatmap para lidar com assincronia
    const arrayFinal: string[] = await lastValueFrom (accounts$.pipe(
      concatMap(accounts => accounts.map(account => account.name)),
      concatMap(name => this.httpClient.get<string>(`${this.API}/income/account/${name}`)),
      toArray())
    )
    return arrayFinal;
  }

  async findAccountsOutcomes(accounts$: Observable<Account[]>) {
    //concatmap para lidar com assincronia
    const arrayFinal: string[] = await lastValueFrom (accounts$.pipe(
      concatMap(accounts => accounts.map(account => account.name)),
      concatMap(name => this.httpClient.get<string>(`${this.API}/outcome/account/${name}`)),
      toArray())
    )
    return arrayFinal;  }
}