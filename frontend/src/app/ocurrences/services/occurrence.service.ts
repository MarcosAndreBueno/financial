import { Injectable } from '@angular/core';
import { Occurrency } from '../model/occurrency';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class OccurrenceService {

  private readonly API = '/api/income';

  constructor(
    private httpClient: HttpClient
  ) {
  }

  onSave(newOccForm: Occurrency) {
    return this.httpClient.post<Occurrency>(this.API, newOccForm); //return observable
  }
}
