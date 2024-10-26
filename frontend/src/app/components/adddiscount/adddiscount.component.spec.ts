import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdddiscountComponent } from './adddiscount.component';

describe('AdddiscountComponent', () => {
  let component: AdddiscountComponent;
  let fixture: ComponentFixture<AdddiscountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdddiscountComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdddiscountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
