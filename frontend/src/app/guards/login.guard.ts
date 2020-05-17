import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class LoginGuard implements CanActivate {

  constructor(
    private router: Router,
    private toastr: ToastrService
  ) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
    // retrieve access token
    const accessToken: String = localStorage.getItem('access_token');
    // if token is exists, then redirect to home
    if (accessToken !== null) {
      this.router.navigate(['/home']);
      this.toastr.info('You are already logged in', 'User Session');
      return false;
    } else {
      return true;
    }
  }
}
