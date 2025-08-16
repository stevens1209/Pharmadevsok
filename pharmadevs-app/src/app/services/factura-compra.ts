import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FacturaCompra } from '../model/factura-compra.model';

@Injectable({
  providedIn: 'root'
})
export class FacturaCompraService {

private baseUrl = 'http://localhost:8080/api/facturacompra';

  constructor(private http:HttpClient){ }

  findAll(): Observable<FacturaCompra[]> {
      return this.http.get<FacturaCompra[]>(this.baseUrl);
    }
  
    findOne(id: number): Observable<FacturaCompra> {
      return this.http.get<FacturaCompra>(`${this.baseUrl}/${id}`);
    }
  
    save(facturaCompra: FacturaCompra): Observable<FacturaCompra> {
      return this.http.post<FacturaCompra>(this.baseUrl, facturaCompra);
    }
  
    update(id: number, facturaCompra: FacturaCompra): Observable<FacturaCompra> {
      return this.http.put<FacturaCompra>(`${this.baseUrl}/${id}`, facturaCompra);
    }
  
    delete(id: number): Observable<void> {
      return this.http.delete<void>(`${this.baseUrl}/${id}`);
    }
  
}