import { TestBed } from '@angular/core/testing';

import { BookingsupplementsService } from './bookingsupplements.service';

describe('BookingsupplementsService', () => {
  let service: BookingsupplementsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BookingsupplementsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
