import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { JwtHelperService } from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root'
})
export class LoginGuard implements CanActivate {

  constructor(
    private router: Router,
    private toastr: ToastrService,
    private jwtHelper: JwtHelperService
  ) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
    // retrieve access token
    const accessToken: string = localStorage.getItem('access_token');

    if (accessToken !== null) {
      // if the token is expired, remove it and return true
      if (this.jwtHelper.isTokenExpired(accessToken)) {
        localStorage.removeItem('access_token');
        return true;
      }
      // else, the token is valid and we can return to homepage
      this.router.navigate(['/home']);
      this.toastr.info('You are already logged in', 'User Session');
      return false;
    } else {
      return true;
    }
  }
}
