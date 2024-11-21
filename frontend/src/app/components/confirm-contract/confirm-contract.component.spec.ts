import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmContractComponent } from './confirm-contract.component';

describe('ConfirmContractComponent', () => {
  let component: ConfirmContractComponent;
  let fixture: ComponentFixture<ConfirmContractComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConfirmContractComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConfirmContractComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
