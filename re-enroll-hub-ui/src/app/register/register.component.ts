import { Component } from '@angular/core';
import {NgForm} from "@angular/forms";
import {StudentService} from "../service/studentService";
import {Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";
import {StudentRegistrationForm} from "../model/studentRegistrationForm";
import {StudentInfo} from "../model/studentInfo";
import {AppService} from "../service/appService";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  loading: boolean = false;
  signInFormView: boolean = true;
  universities: String[] = [];
  loginModel = {email: "", password: ""};
  registerModel = {university: "", email: "", password: "", confirmPassword: ""};
  studentRegistrationForm = new StudentRegistrationForm('', '', '', '');

  constructor(private studentService: StudentService, private appService: AppService, private router: Router) {
  }

  public login(form: NgForm): void {
    if (this.loginModel.email && this.loginModel.password) {
      this.studentService.loginStudent(this.loginModel.email, this.loginModel.password)
        .subscribe(
          (res: StudentInfo) => {
            if (res.email !== null) {
              this.studentService.setSignIn(res);
              this.router.navigateByUrl('home');
            } else {
              alert("Bad credentials provided");
            }
          },
          (error: HttpErrorResponse) => {
            alert("Could not log student in");
          }
        );
    } else {
      alert("Please make sure Email and Password is provided");
    }
    form.resetForm();
  }

  public register(form: NgForm): void {
    this.studentRegistrationForm.email = this.registerModel.email;
    this.studentRegistrationForm.university = this.registerModel.university;
    this.studentRegistrationForm.password = this.registerModel.password;
    this.studentRegistrationForm.confirmPassword = this.registerModel.confirmPassword;
    if (this.isValidRegistrationForm(this.studentRegistrationForm)) {
      this.studentService.registerStudent(this.studentRegistrationForm)
        .subscribe(
          (res: StudentInfo) => {
                if (res.email !== null) {
                  this.studentService.setSignIn(res);
                  form.resetForm();
                  this.router.navigateByUrl('home');
                } else {
                  alert("Registration cold not be completed");
                }
          },
          (error: HttpErrorResponse) => {
            alert(error.error.message);
          }
        );
    } else {
      alert("Please make sure the registration form is filled with Password and ConfirmPassword matching");
    }
  }

  private isValidRegistrationForm(form: StudentRegistrationForm) {
    return form.email && form.university && form.password && form.confirmPassword;
  }

  switchForm() {
    this.signInFormView = !this.signInFormView;
    if(!this.signInFormView) {
      this.loadUniversities();
    }
  }

  private loadUniversities() {
    if(this.universities.length < 2) {
      this.appService.loadUniversities()
        .subscribe(
          (res: String[]) => {
            this.universities = res;
          },
          (error: HttpErrorResponse) => {
            alert("Could not load universities");
          }
        );
    }
  }

}
