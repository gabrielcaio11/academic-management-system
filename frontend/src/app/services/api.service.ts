import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private readonly apiUrl = environment.apiUrl;

  constructor(private readonly http: HttpClient) {}

  getAlunos(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/alunos`);
  }

  createAluno(payload: { nome: string; email: string }): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/alunos`, payload);
  }
}


