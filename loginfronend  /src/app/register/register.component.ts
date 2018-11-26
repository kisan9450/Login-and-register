import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { FormGroup, FormBuilder, Validators, NgForm, FormControl } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: User = new User();

  constructor(private formBuilder: FormBuilder, private userBack:UserService) { }

  ngOnInit() {
  }

  submitted = false;

  onSubmit() { 
    this.submitted = true;     
  }

  addUsers(user:User){
    var userCast={
    "sid":"562b2649b2e70464f113c04d",
    "userName": this.user.userName,
    "password": this.user.password,
    "email": this.user.email,
    "phonenumber": this.user.phonenumber,
    "applicationname": this.user.applicationname,
    "dependency": this.user.dependency
    }
    this.userBack.addUser(userCast).subscribe();
    }

  registrationform = new FormGroup({
      usernameform: new FormControl(),
      passwordform: new FormControl(),
      emailform: new FormControl(),
      phonenumberform: new FormControl(),
      applicationnameform : new FormControl(),
      dependencyform: new FormControl()
   });

}
