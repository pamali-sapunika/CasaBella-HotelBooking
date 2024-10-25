import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddseasonalroomsComponent } from './addseasonalrooms.component';

describe('AddseasonalroomsComponent', () => {
  let component: AddseasonalroomsComponent;
  let fixture: ComponentFixture<AddseasonalroomsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddseasonalroomsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddseasonalroomsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
