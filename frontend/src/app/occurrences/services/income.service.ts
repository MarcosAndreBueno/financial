import { Injectable } from '@angular/core';
import { Occurrence } from '../model/occurrence';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class IncomeService {

  private readonly API = '/api/income';

  constructor(
    private httpClient: HttpClient
  ) {
  }

  onSave(newOccForm: Occurrence) {
    return this.httpClient.post<Occurrence>(this.API, newOccForm); //return observable
  }

  list() {
    return this.httpClient.get<Occurrence[]>(this.API)
  }
}
