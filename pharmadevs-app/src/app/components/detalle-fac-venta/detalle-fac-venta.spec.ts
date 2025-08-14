import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalleFacVenta } from './detalle-fac-venta';

describe('DetalleFacVenta', () => {
  let component: DetalleFacVenta;
  let fixture: ComponentFixture<DetalleFacVenta>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DetalleFacVenta]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetalleFacVenta);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
