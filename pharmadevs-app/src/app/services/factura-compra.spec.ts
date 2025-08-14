import { TestBed } from '@angular/core/testing';

import { FacturaCompra } from './factura-compra';

describe('FacturaCompra', () => {
  let service: FacturaCompra;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FacturaCompra);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
