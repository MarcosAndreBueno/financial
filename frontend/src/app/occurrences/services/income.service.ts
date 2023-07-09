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
  
  loadById(id: string) {
    //concatenação echma script
    return this.httpClient.get<Occurrence>(`${this.API}/${id}`);  

  }

  onEdit(editOccForm: Partial<Occurrence>, id: number) {
    return this.httpClient.put<Occurrence>(`${this.API}/${id}`, editOccForm);
  }

  deleteById(id: string) {
    return this.httpClient.delete(`${this.API}/${id}`);
  }
}
