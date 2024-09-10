import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GenericResponse } from '../model/generic-response';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CiudadService {
  private apiUrl = 'http://localhost:8080/ciudad';

constructor(private http: HttpClient) { }
  
  getAll(): Observable<GenericResponse> {
    return this.http.get<GenericResponse>(`${this.apiUrl}/listar`);
  }
}

