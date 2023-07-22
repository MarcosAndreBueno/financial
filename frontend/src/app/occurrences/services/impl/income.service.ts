import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { OccurrenceService } from '../occurrence.service';
import { Occurrence } from '../../model/occurrence';


@Injectable(
  {
  providedIn: 'root'
}
)
export class IncomeService implements OccurrenceService {
  private readonly API = '/api/income';

  constructor(
    private httpClient: HttpClient
  ) {
  }

  onSave(newOccForm: Occurrence) {
    return this.httpClient.post<Occurrence>(this.API, newOccForm); //return observable
  }

  list(month: number, year: number) {
    return this.httpClient.get<Occurrence[]>(`${this.API}/${month}/${year}`)
  }
  
  loadById(id: string) {
    //concatenação echma script
    return this.httpClient.get<Occurrence>(`${this.API}/${id}`);  
  }

  onEdit(editOccForm: Partial<Occurrence>, id: number) {
    return this.httpClient.put<Occurrence>(`${this.API}/${id}`, editOccForm);
  }

  deleteById(id: string) {
    return this.httpClient.delete<Occurrence>(`${this.API}/${id}`);
  }
}
