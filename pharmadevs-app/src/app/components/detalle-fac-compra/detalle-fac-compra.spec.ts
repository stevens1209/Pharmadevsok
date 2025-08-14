import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalleFacCompra } from './detalle-fac-compra';

describe('DetalleFacCompra', () => {
  let component: DetalleFacCompra;
  let fixture: ComponentFixture<DetalleFacCompra>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DetalleFacCompra]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetalleFacCompra);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
