import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Produccion } from '../model/produccion.model';

@Injectable({
  providedIn: 'root'
})
export class ProduccionService {

  private baseUrl = 'http://localhost:8080/Produccion';

  constructor(private http: HttpClient) { }

  findAll(): Observable<Produccion[]> {
    return this.http.get<Produccion[]>(this.baseUrl);
  }

  findOne(id: number): Observable<Produccion> {
    return this.http.get<Produccion>(`${this.baseUrl}/${id}`);
  }

  save(produccion: Produccion): Observable<Produccion> {
    return this.http.post<Produccion>(this.baseUrl, produccion);
  }

  update(id: number, produccion: Produccion): Observable<Produccion> {
    return this.http.put<Produccion>(`${this.baseUrl}/${id}`, produccion);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
