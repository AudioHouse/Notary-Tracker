import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class MainGuard implements CanActivate {

  constructor(
    private router: Router,
    private toastr: ToastrService,
    private jwtHelper: JwtHelperService) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {

    const authToken: string = localStorage.getItem('access_token');

    if (authToken == null) {
      this.router.navigate(['/login']);
      return false;
    } else if (this.jwtHelper.isTokenExpired(authToken)) {
      localStorage.removeItem('access_token');
      this.router.navigate(['/login']);
      this.toastr.warning('Your user session has expired', 'Session Expired');
      return false;
    } else {
      return true;
    }
  }
}
