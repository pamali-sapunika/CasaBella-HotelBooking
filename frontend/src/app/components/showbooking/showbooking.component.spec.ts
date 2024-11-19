import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowbookingComponent } from './showbooking.component';

describe('ShowbookingComponent', () => {
  let component: ShowbookingComponent;
  let fixture: ComponentFixture<ShowbookingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShowbookingComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowbookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
