import { TestBed } from '@angular/core/testing';
import { ResolveFn } from '@angular/router';

import { incomeResolver } from './income.resolver';

describe('incomeResolver', () => {
  const executeResolver: ResolveFn<boolean> = (...resolverParameters) => 
      TestBed.runInInjectionContext(() => incomeResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeResolver).toBeTruthy();
  });
});
