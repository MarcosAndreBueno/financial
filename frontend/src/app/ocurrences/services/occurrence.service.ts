import { Injectable } from '@angular/core';
import { Occurrency } from '../model/occurrency';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class OccurrenceService {

  private readonly API = '/api/url'

  constructor(
    private httpClient: HttpClient
  ) {
  }

  onSave(newOccForm: Occurrency) {
    //console.log(newOccForm);
    return this.httpClient.post(this.API, newOccForm); //return observable
  }
}
