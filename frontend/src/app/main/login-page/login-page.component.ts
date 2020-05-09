import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {
    email: string;
    password: string;
    loading: boolean;

  constructor(
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loading = false;
  }

  login(): void {
    this.router.navigate(["/home"])
  }

  buttonText(): String {
    return (this.loading ? "Loading..." : "Login");
  }
}
