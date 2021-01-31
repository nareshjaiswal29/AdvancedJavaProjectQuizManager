import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuetionListComponent } from './quetion-list.component';

describe('QuetionListComponent', () => {
  let component: QuetionListComponent;
  let fixture: ComponentFixture<QuetionListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuetionListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(QuetionListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
