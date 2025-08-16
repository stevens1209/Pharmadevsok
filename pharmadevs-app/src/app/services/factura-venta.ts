import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FacturaVenta } from '../model/factura-venta.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FacturaVentaService {
  
  private baseUrl = 'http://localhost:8080/api/FacturaVenta';

  constructor(private http: HttpClient){ }

  findAll(): Observable<FacturaVenta[]> {
      return this.http.get<FacturaVenta[]>(this.baseUrl);
    }
  
    findOne(id: number): Observable<FacturaVenta> {
      return this.http.get<FacturaVenta>(`${this.baseUrl}/${id}`);
    }
  
    save(facturaVenta: FacturaVenta): Observable<FacturaVenta> {
      return this.http.post<FacturaVenta>(this.baseUrl, facturaVenta);
    }
  
    update(id: number, facturaVenta: FacturaVenta): Observable<FacturaVenta> {
      return this.http.put<FacturaVenta>(`${this.baseUrl}/${id}`, facturaVenta);
    }
  
    delete(id: number): Observable<void> {
      return this.http.delete<void>(`${this.baseUrl}/${id}`);
    }
}