import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  //instância disponível globalmente
  providedIn: 'root'
})
export class CategoryService {

  //injeção http para requisição ajax
  constructor(private httpClient: HttpClient) { }

  //type any
  list() {
    return [{ id: 1, category: 'Home' }];
  }
}
