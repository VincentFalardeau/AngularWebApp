import { TestBed } from '@angular/core/testing';

import { MarkValidationService } from './mark-validation.service';

describe('MarkValidationService', () => {
  let service: MarkValidationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MarkValidationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
