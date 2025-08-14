
import { Proveedor } from "./proveedor.model";

export interface Bodega {
    idBodega: number;
    nombrematerial: string;
    cantidad: number;
    fecharecepcion: Date;
    proveedor: Proveedor

    [key:string]: any;
}