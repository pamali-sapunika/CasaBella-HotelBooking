import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelContractsComponent } from './hotel-contracts.component';

describe('HotelContractsComponent', () => {
  let component: HotelContractsComponent;
  let fixture: ComponentFixture<HotelContractsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HotelContractsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HotelContractsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
