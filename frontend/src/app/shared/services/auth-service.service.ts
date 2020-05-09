import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private httpClient: HttpClient
  ) { }

  postToken(email: string, password: string): Observable<any> {
    const requestBody = {
      email,
      password
    }
    console.log("The request body is: " + JSON.stringify(requestBody));
    return this.httpClient.post("http://localhost:8080/token", requestBody);
  }

}
