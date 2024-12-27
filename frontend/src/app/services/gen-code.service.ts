import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GenCodeService {
  private apiUrl = 'http://localhost:8888/CODEGEN-SERVICE/api/codegen';

  constructor(private http: HttpClient) {}

  generateCode(templateId: number, projectId: number, fieldValues: any, token: string): Observable<any> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    const body = { ...fieldValues };

    return this.http.post(`${this.apiUrl}/${templateId}?projectId=${projectId}`, body, { headers });
  }

  getGeneratedCodesForProject(projectId: number, token: string): Observable<any[]> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<any[]>(`http://localhost:8888/CODEGEN-SERVICE/api/codegen/project/${projectId}`, { headers });
  }
  
  getGeneratedCodeDetails(fileId: number, token: string): Observable<any> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<any>(`http://localhost:8888/CODEGEN-SERVICE/api/codegen/file/${fileId}`, { headers });
  }
  
}
