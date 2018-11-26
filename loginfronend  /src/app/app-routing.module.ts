import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login/login/login.component';
import { DashboardComponent } from './dashboard/dashboard/dashboard.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  {
      path: 'home',
      component: HomeComponent
  },
  {
    path: 'auth/login',
    component: LoginComponent
},
{
  path: '',
  redirectTo: 'home',
  pathMatch: 'full'
},
{
 path: 'dashboard',
 component: DashboardComponent 
},
{ 
path: 'register',
component: RegisterComponent
 },];
@NgModule({
  imports: [
    RouterModule,
    RouterModule.forRoot(routes)
  ],
  declarations: [],
  exports: [RouterModule]
})
export class AppRoutingModule { }
