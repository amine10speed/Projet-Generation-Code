import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private loginUrl = 'http://localhost:8888/USER-SERVICE/api/users/login';
  private signupUrl = 'http://localhost:8888/USER-SERVICE/api/users/register';
  private meUrl = 'http://localhost:8888/USER-SERVICE/api/users/me';

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<string> {
    return this.http.post(this.loginUrl, { username, password }, { responseType: 'text' });
  }

  signup(username: string, password: string): Observable<any> {
    return this.http.post(this.signupUrl, { username, password });
  }

  getCurrentUser(): Observable<any> {
    const token = localStorage.getItem('token');
    console.log('Token sent in Authorization header:', token);
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<any>(this.meUrl, { headers });
  }
}
