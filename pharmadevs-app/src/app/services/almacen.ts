import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Almacen } from '../model/almacen.model';

@Injectable({
  providedIn: 'root'
})
export class AlmacenService {
  
  private baseUrl = 'http://localhost:8080/api/almacen';

  constructor(private http: HttpClient){ }

  findAll(almacen?: Almacen): Observable<Almacen[]> {
      return this.http.get<Almacen[]>(this.baseUrl);
    }
  
    findOne(id: number): Observable<Almacen> {
      return this.http.get<Almacen>(`${this.baseUrl}/${id}`);
    }
  
    save(almacen: Almacen): Observable<Almacen> {
      return this.http.post<Almacen>(this.baseUrl, almacen);
    }
  
    update(id: number, almacen: Almacen): Observable<Almacen> {
      return this.http.put<Almacen>(`${this.baseUrl}/${id}`, almacen);
    }
  
    delete(id: number): Observable<void> {
      return this.http.delete<void>(`${this.baseUrl}/${id}`);
    }
  
}