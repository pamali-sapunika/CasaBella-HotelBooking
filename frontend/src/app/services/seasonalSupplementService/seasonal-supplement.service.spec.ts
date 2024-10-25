import { TestBed } from '@angular/core/testing';

import { SeasonalSupplementService } from './seasonal-supplement.service';

describe('SeasonalSupplementService', () => {
  let service: SeasonalSupplementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SeasonalSupplementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
