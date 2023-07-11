import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewOccurrenceComponent } from './new-occurrence.component';

describe('NewCategoryComponent', () => {
  let component: NewOccurrenceComponent;
  let fixture: ComponentFixture<NewOccurrenceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NewOccurrenceComponent]
    });
    fixture = TestBed.createComponent(NewOccurrenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
