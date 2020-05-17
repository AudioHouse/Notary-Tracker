import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-main-navbar',
  templateUrl: './main-navbar.component.html',
  styleUrls: ['./main-navbar.component.scss']
})
export class MainNavbarComponent implements OnInit {

  constructor(
    private router: Router,
    private toastr: ToastrService
    ) { }

  ngOnInit(): void {
  }

  goHome(): void {
    this.router.navigate(["/home"]);
  }

  goSchedule(): void {
    this.router.navigate(["/home"]);
  }

  logout(): void {
    localStorage.removeItem('access_token');
    this.router.navigate(['/login']);
    this.toastr.success('You have been successfully signed out', 'Signed Out');
  }

}
