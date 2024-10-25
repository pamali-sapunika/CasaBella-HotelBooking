import { TestBed } from '@angular/core/testing';

import { SeasonalRoomsService } from './seasonal-rooms.service';

describe('SeasonalRoomsService', () => {
  let service: SeasonalRoomsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SeasonalRoomsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
