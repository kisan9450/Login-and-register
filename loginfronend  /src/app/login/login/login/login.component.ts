import { Component, OnInit, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthLoginInfo } from 'src/app/login/auth/login-info';
import { AuthService } from 'src/app/login/auth/auth.service';
import { TokenStorageService } from 'src/app/login/auth/token-storage.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private user: AuthLoginInfo = new AuthLoginInfo();
  loginForm: FormGroup;
  hide = true;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  token: string;
  userName: string;


  constructor(private formBuilder: FormBuilder, private authService: AuthService, private tokenStorageService: TokenStorageService) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      'userName': [this.user.userName, [
        Validators.required,
      ]],
      'password': [this.user.password, [
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(30)
      ]]
    });
    if (this.tokenStorageService.getToken()) {
      this.isLoggedIn = true;
      this.token = this.tokenStorageService.getToken();
      this.userName = this.tokenStorageService.getUserName();
    }
  }

  onLoginSubmit() {
    console.log(this.loginForm.value);
    this.authService.submit(this.loginForm.value).subscribe( data => {
      this.tokenStorageService.saveToken(data.token);
      this.tokenStorageService.saveUserName(data.username);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.reloadPage();
      }
      ,
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      }
    );
  }
  reloadPage() {
    window.location.reload();
  }
}
