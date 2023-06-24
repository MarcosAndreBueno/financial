import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Category } from '../model/category';

@Injectable({
  //instância disponível globalmente
  providedIn: 'root'
})
export class CategoryService {

  private readonly API = '/assets/category.json';

  //injeção http para requisição ajax
  constructor(private httpClient: HttpClient) { }

  //type observable
  list() {
    return this.httpClient.get(this.API);
  }
}
