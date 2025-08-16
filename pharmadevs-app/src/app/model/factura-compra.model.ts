
import { Proveedor } from "./proveedor.model";

export interface FacturaCompra {
    idFacturacompra:number;
    fecha: Date;
    montototal: number;
    faccompra?: string;
    proveedor: Proveedor

    [key: string]: any;
}