import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OccurrencesComponent } from './occurrences.component';

describe('OccurrencesComponent', () => {
  let component: OccurrencesComponent;
  let fixture: ComponentFixture<OccurrencesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OccurrencesComponent]
    });
    fixture = TestBed.createComponent(OccurrencesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
