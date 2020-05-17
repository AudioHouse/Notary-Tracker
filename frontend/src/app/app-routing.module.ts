import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomepageComponent } from './main/homepage/homepage.component';
import { LoginPageComponent } from './main/login-page/login-page.component';
import { LoginGuard } from './guards/login.guard';

const routes: Routes = [
  { path: 'home', component: HomepageComponent},
  { path: 'login', component: LoginPageComponent, canActivate: [LoginGuard]},
  { path: '',   redirectTo: '/home', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
