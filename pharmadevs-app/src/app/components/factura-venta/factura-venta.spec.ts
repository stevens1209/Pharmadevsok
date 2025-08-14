import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FacturaVenta } from './factura-venta';

describe('FacturaVenta', () => {
  let component: FacturaVenta;
  let fixture: ComponentFixture<FacturaVenta>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FacturaVenta]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FacturaVenta);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
