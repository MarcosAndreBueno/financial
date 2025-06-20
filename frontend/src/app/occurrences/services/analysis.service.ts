import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Category } from '../model/category';
import { first, tap } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class AnalysisService {

    private readonly API = '/api/analysis';

    constructor(private httpClient: HttpClient) { }

    public getCategoryExpenseIncreaseLastMonth(): Observable<{ [key: string]: number }> {
        return this.httpClient.get<{ [key: string]: number }>(`${this.API}/getCategoryExpenseIncreaseLastMonth`);
    }
}
