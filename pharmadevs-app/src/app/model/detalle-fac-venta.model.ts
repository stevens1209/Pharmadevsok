import { FacturaVenta } from "./factura-venta.model";
import { Producto } from "./producto.model";

export interface DetalleFacVenta {
    idDetalleVenta:number;
    cantidad: number;
    preciounitario: number;
    iva: number;
    subtotal: number;
    producto: Producto;
    facturaventa: FacturaVenta;

    [key: string]: any;
}