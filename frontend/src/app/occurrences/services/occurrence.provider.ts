import { Injectable } from "@angular/core";
import { IncomeService } from "./impl/income.service";
import { OutcomeService } from "./impl/outcome.service";
import { OccurrenceService } from "./occurrence.service";
import { HttpClient } from "@angular/common/http";

@Injectable({
    providedIn: 'root',
})
export class OccurrenceFactory {

    constructor(private httpClient: HttpClient) { }

    getInstanceOf(url: string | undefined): OccurrenceService {
        if (url && url.includes('occurrences/incomes')) {
            return new IncomeService(this.httpClient);
        } else if (url && url.includes('outcome')) {
            return new OutcomeService(this.httpClient);
        } else {
            throw new Error('Invalid URL');
        }
    }
}