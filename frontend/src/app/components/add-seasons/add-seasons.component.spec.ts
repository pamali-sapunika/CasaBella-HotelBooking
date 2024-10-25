import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSeasonsComponent } from './add-seasons.component';

describe('AddSeasonsComponent', () => {
  let component: AddSeasonsComponent;
  let fixture: ComponentFixture<AddSeasonsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddSeasonsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddSeasonsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
