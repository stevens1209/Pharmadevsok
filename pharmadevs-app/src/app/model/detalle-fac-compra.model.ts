import { FacturaCompra } from "./factura-compra.model";
import { Producto } from "./producto.model";

export interface DetalleFacCompra {
    idDetallecompra:number;
    cantidad: number;
    preciounitario: number;
    iva: number;
    subtotal: number;
    producto: Producto;
    facturacompra: FacturaCompra;

    [key: string]: any;
}