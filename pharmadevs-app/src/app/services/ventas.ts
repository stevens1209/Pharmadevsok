import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ventas } from '../model/ventas.model';

@Injectable({
  providedIn: 'root'
})
export class VentasService {
  private baseUrl = 'http://localhost:8080/Ventas'
  constructor(private http: HttpClient){}

  findAll(ventas?: Ventas): Observable<Ventas[]>{
    return this.http.get<Ventas[]>(this.baseUrl);
  }

   findOne(id: number): Observable<Ventas> {
      return this.http.get<Ventas>(`${this.baseUrl}/${id}`);
    }
  
    save(ventas: Ventas): Observable<Ventas> {
      return this.http.post<Ventas>(this.baseUrl, ventas);
    }
  
    update(id: number, ventas: Ventas): Observable<Ventas> {
      return this.http.put<Ventas>(`${this.baseUrl}/${id}`, ventas);
    }
  
    delete(id: number): Observable<void> {
      return this.http.delete<void>(`${this.baseUrl}/${id}`);
    }



 
}
