import { TestBed } from '@angular/core/testing';
import { ResolveFn } from '@angular/router';

import { occurrenceResolver } from './occurrence.resolver';

describe('occurrenceResolver', () => {
  const executeResolver: ResolveFn<boolean> = (...resolverParameters) => 
      TestBed.runInInjectionContext(() => occurrenceResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeResolver).toBeTruthy();
  });
});
