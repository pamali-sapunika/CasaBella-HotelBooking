import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShownewcontractComponent } from './shownewcontract.component';

describe('ShownewcontractComponent', () => {
  let component: ShownewcontractComponent;
  let fixture: ComponentFixture<ShownewcontractComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShownewcontractComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShownewcontractComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
