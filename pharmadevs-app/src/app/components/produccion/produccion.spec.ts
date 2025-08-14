import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProduccionComponent } from './produccion';

describe('Produccion', () => {
  let component: ProduccionComponent;
  let fixture: ComponentFixture<ProduccionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProduccionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProduccionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
