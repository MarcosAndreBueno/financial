import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewIncomeComponent } from './new-income.component';

describe('NewCategoryComponent', () => {
  let component: NewIncomeComponent;
  let fixture: ComponentFixture<NewIncomeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NewIncomeComponent]
    });
    fixture = TestBed.createComponent(NewIncomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
