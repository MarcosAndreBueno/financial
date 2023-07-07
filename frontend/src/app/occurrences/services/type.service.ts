import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Type } from '../model/type';
import { first, tap } from 'rxjs/operators';

@Injectable({
  //instância disponível globalmente
  providedIn: 'root'
})
export class TypeService {

  //debug: devtools -> network -> headers -> request url
  private readonly API = '/api/type';

  //injeção http para requisição ajax
  constructor(private httpClient: HttpClient) { }

  //type observable parametrizado
  list() {
    return this.httpClient.get<Type[]>(this.API)
      .pipe(
        first()
      );
  }
}
