
import { Cliente } from "./cliente.model";

export interface FacturaVenta {
    idFacturaventa:number;
    fecha: Date;
    montototal: number;
   cliente: Cliente;

    [key: string]: any;
}