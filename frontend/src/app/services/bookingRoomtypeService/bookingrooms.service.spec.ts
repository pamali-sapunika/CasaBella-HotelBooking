import { TestBed } from '@angular/core/testing';

import { BookingroomsService } from './bookingrooms.service';

describe('BookingroomsService', () => {
  let service: BookingroomsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BookingroomsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
