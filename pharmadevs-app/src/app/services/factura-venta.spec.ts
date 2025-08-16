import { TestBed } from '@angular/core/testing';

import { FacturaVentaService } from './factura-venta';

describe('FacturaVenta', () => {
  let service: FacturaVentaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FacturaVentaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});