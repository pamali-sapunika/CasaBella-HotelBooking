import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchagainComponent } from './searchagain.component';

describe('SearchagainComponent', () => {
  let component: SearchagainComponent;
  let fixture: ComponentFixture<SearchagainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchagainComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchagainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
