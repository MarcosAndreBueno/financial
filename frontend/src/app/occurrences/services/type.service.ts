import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Type } from '../model/type';
import { first } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TypeService {

  private readonly API = '/api/type';

  constructor(private httpClient: HttpClient) { }

  list() {
    return this.httpClient.get<Type[]>(this.API)
      .pipe(
        first()
      );
  }

  onSave(newCatForm: Type) {
    return this.httpClient.post<Type>(this.API, newCatForm);
  }

  onEdit(editCatForm: Partial<Type>, id: number) {
    return this.httpClient.put<Type>(`${this.API}/${id}`, editCatForm);
  }

  deleteById(id: number) {
    return this.httpClient.delete<Type>(`${this.API}/${id}`);
  }
}
