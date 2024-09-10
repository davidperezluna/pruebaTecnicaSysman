import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GenericResponse } from '../model/generic-response';
import { HttpClient } from '@angular/common/http';
import { Material } from '../model/material.model';
import { Filter } from '../model/filter.model';

@Injectable({
  providedIn: 'root'
})
export class MaterialService {
  private apiUrl = 'http://localhost:8080/material';

constructor(private http: HttpClient) { }
  
  getAll(): Observable<GenericResponse> {
    return this.http.get<GenericResponse>(`${this.apiUrl}/listar`);
  }

  crear(material: Material): Observable<GenericResponse> {
    return this.http.post<GenericResponse>(`${this.apiUrl}/crear`, material);
  }

  actualizar(material: Material): Observable<GenericResponse> {
    return this.http.put<GenericResponse>(`${this.apiUrl}/editar`, material);
  }

  findByCiudad(filter: Filter): Observable<GenericResponse> {
    return this.http.post<GenericResponse>(`${this.apiUrl}/findByCiudad`,filter);
  }
  findByTipoAndFechaCompra(filter: Filter): Observable<GenericResponse> {
    return this.http.post<GenericResponse>(`${this.apiUrl}/findByTipoAndFechaCompra`,filter);
  }
}



