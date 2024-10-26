import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddseasonalsuppleComponent } from './addseasonalsupple.component';

describe('AddseasonalsuppleComponent', () => {
  let component: AddseasonalsuppleComponent;
  let fixture: ComponentFixture<AddseasonalsuppleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddseasonalsuppleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddseasonalsuppleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
