
import { Cliente } from "./cliente.model";

export interface FacturaVenta {
    idFacturaventa:number;
    fecha: Date;
    montototal: number;
    facventa?: string;
    cliente: Cliente;

    [key: string]: any;
}