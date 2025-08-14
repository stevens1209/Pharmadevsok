import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FacturaCompra } from './factura-compra';

describe('FacturaCompra', () => {
  let component: FacturaCompra;
  let fixture: ComponentFixture<FacturaCompra>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FacturaCompra]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FacturaCompra);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
