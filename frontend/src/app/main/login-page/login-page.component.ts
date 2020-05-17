import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../shared/services/auth-service.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';

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
    private router: Router,
    private authService: AuthService,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
    this.loading = false;
  }

  login(): void {
    this.loading = true;
    if (this.email == undefined || this.password == undefined) {
      this.toastr.error('Email and Password fields cannot be blank', 'Login Error');
      this.loading = false;
      return;
    }
    this.authService.postToken(this.email, this.password).subscribe(response => {
      // save the token locally
      console.log('Response was: ' + JSON.stringify(response));
      localStorage.setItem('access_token', response.authToken);
      // navigate to homepage
      this.router.navigate(['/home'])
      // no longer loading
      this.loading = false;
    }, (error: HttpErrorResponse) => {
      // stop loading 
      this.loading = false;
      // clear the password field
      this.password = "";
      // print the error to user
      this.toastr.error(error.error.message, 'Login Error');
    });
  }

  buttonText(): String {
    return (this.loading ? "Loading..." : "Login");
  }
}
