import {Component, OnInit} from '@angular/core';
import {StudentService} from "../service/studentService";
import {Router} from "@angular/router";
import {StudentInfo} from "../model/studentInfo";

@Component({
  selector: 'app-progress',
  templateUrl: './progress.component.html',
  styleUrls: ['./progress.component.css']
})
export class ProgressComponent implements OnInit {

  student: StudentInfo = new StudentInfo('','','','','','','','');
  steps: string[] = ['NOT_SUBMITTED', 'PENDING', 'CANCELLED', 'REJECTED', 'ACCEPTED'];
  activeStatusIndex: number = 2;

  constructor(private studentService: StudentService, private router: Router) {}

  ngOnInit(): void {
    if(!this.isSignedIn) {
      this.router.navigate([`login`]);
    }
    this.student = this.studentService.getSignInUser();
    this.activeStatusIndex = this.steps.indexOf(this.student.applicationStatus);
  }

  get isSignedIn() {
    return this.studentService.isSignedIn();
  }

}
