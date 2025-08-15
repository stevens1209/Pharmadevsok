import { Cliente } from "./cliente.model";
import { FacturaVenta } from "./factura-venta.model"

export interface Ventas {
  idVentas: number;
  fechaventa: Date;
  formapago: String;
  totalventa: Number;
  cliente: Cliente;
  facturaventa: FacturaVenta;

  [key: string]: any;
}
