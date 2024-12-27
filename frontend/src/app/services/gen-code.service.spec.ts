import { TestBed } from '@angular/core/testing';

import { GenCodeService } from './gen-code.service';

describe('GenCodeService', () => {
  let service: GenCodeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GenCodeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
