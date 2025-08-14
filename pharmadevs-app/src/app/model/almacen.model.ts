import { Producto } from "./producto.model";

export interface Almacen{
    idAlmacen: number;
    stock: string;
    ubicacion: string;
    imagen?: string;
    producto: Producto;

    [key: string]: any;
}