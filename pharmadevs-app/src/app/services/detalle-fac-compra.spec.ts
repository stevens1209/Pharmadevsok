import { TestBed } from '@angular/core/testing';

import { DetalleFacCompra } from './detalle-fac-compra';

describe('DetalleFacCompra', () => {
  let service: DetalleFacCompra;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DetalleFacCompra);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
