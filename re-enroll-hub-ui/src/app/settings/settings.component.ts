import { Component, OnInit } from '@angular/core';
import {StudentService} from "../service/studentService";
import {Router} from "@angular/router";
import {StudentInfo} from "../model/studentInfo";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  status: string = '';
  isEnabled: boolean = false;
  student: StudentInfo = new StudentInfo('','','','','','','','');
  studentCopy: StudentInfo = new StudentInfo('','','','','','','','');

  constructor(private studentService: StudentService, private router: Router) {
  }

  ngOnInit(): void {
    if(!this.isSignedIn) {
      this.router.navigate([`login`]);
    }
    let studentInfo = this.studentService.getSignInUser();
    this.student = studentInfo;
    this.studentCopy = studentInfo;
    this.status = 'STATUS:  ' + this.student.applicationStatus;
  }

  get isSignedIn() {
    return this.studentService.isSignedIn();
  }

  updateStudent() {
    this.studentService.updateStudent(this.student.email, this.student.altEmail, this.student.contactNumber, this.student.address)
      .subscribe(
        (res: StudentInfo) => {
          alert('Details updated successfully');
          this.studentService.setSignIn(res);
          this.status = 'STATUS:  ' + res.applicationStatus;
        },
        (error: HttpErrorResponse) => {
          alert(error.error.message);
        }
      );
  }

  cancelApplication() {
    this.studentService.cancelApplication(this.student.email)
      .subscribe(
        (res: StudentInfo) => {
            alert('Application cancelled successfully');
            this.studentService.setSignIn(res);
            this.isEnabled = true;
        },
        (error: HttpErrorResponse) => {
          alert(error.error.message);
        }
      );
  }

  canCancelApplication() {
    return this.student.applicationStatus === 'PENDING' ||
      this.student.applicationStatus === 'ACCEPTED';
  }

}
