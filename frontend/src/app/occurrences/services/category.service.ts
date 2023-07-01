import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Category } from '../model/category';
import { first, tap } from 'rxjs/operators';

@Injectable({
  //instância disponível globalmente
  providedIn: 'root'
})
export class CategoryService {

  //debug: devtools -> network -> headers -> request url
  private readonly API = '/api/category';

  //injeção http para requisição ajax
  constructor(private httpClient: HttpClient) { }

  //type observable parametrizado
  list() {
    return this.httpClient.get<Category[]>(this.API)
    //manipular informação
    .pipe(
      //fechar conexão
      first(), 
      //debug
      tap(courses => console.log(courses))
    );
  }
}
