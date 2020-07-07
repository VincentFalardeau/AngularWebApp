import { TestBed } from '@angular/core/testing';

import { GlobalGradeService } from './global-grade.service';

describe('GlobalGradeService', () => {
  let service: GlobalGradeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GlobalGradeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
