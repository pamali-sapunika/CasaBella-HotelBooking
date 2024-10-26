import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeasonalitemsComponent } from './seasonalitems.component';

describe('SeasonalitemsComponent', () => {
  let component: SeasonalitemsComponent;
  let fixture: ComponentFixture<SeasonalitemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SeasonalitemsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SeasonalitemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
