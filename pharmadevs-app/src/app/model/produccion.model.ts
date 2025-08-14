import { Producto } from "./producto.model";

export interface Produccion {
  idProduccion: number;
  fechaproduccion: Date;
  fechavencimiento: Date;
  lote: string;
  cantidadproducida: number;
  producto: Producto;
  imagen?: string;  // <-- agregar campo para ruta de imagen

  [key: string]: any;
}
