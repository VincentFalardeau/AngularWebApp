import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GlobalGradeComponent } from './global-grade.component';

describe('GlobalGradeComponent', () => {
  let component: GlobalGradeComponent;
  let fixture: ComponentFixture<GlobalGradeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GlobalGradeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GlobalGradeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
