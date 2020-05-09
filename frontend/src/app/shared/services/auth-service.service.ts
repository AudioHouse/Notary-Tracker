import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  constructor(
    private httpClient: HttpClient
  ) { }

  postToken(email: string, password: string): Observable<any> {
    const requestBody = {
      email,
      password 
    }
    return this.httpClient.post("http://localhost:8080/token", requestBody);
  }

}
