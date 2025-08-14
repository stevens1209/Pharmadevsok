import { TestBed } from '@angular/core/testing';

import { FacturaVenta } from './factura-venta';

describe('FacturaVenta', () => {
  let service: FacturaVenta;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FacturaVenta);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
