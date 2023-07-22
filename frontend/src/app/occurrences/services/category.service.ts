import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Category } from '../model/category';
import { first, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private readonly API = '/api/category';

  constructor(private httpClient: HttpClient) { }

  list() {
    return this.httpClient.get<Category[]>(this.API)
      .pipe(
        first()
      );
  }

  onSave(newCatForm: Category) {
    return this.httpClient.post<Category>(this.API, newCatForm); //return observable
  }

  onEdit(editCatForm: Partial<Category>, id: number) {
    return this.httpClient.put<Category>(`${this.API}/${id}`, editCatForm);
  }

  deleteById(id: number) {
    return this.httpClient.delete<Category>(`${this.API}/${id}`);
  }
}
