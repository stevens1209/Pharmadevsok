import { TestBed } from '@angular/core/testing';

import { DetalleFacVenta } from './detalle-fac-venta';

describe('DetalleFacVenta', () => {
  let service: DetalleFacVenta;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DetalleFacVenta);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
