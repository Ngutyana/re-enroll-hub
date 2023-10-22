import {Component, OnInit} from '@angular/core';
import {StudentService} from "../service/studentService";
import {StudentInfo} from "../model/studentInfo";

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})

export class LandingComponent implements OnInit {

  student: StudentInfo = new StudentInfo('','','','','','','','');

  constructor(private studentService: StudentService) {}

  ngOnInit(): void {
    if(this.isSignedIn) {
      this.student = this.studentService.getSignInUser();
    }
  }

  get isSignedIn() {
     return this.studentService.isSignedIn()
  }

  getLogo() {
    if(this.student.university === 'Cape Peninsula University of Technology'){
      return '../assets/logos/cput.png'
    }
    if(this.student.university === 'Rhodes University'){
      return '../assets/logos/rhodes.png'
    }
    if(this.student.university === 'University of Johannesburg'){
      return '../assets/logos/uj.png'
    }
    return '../assets/logos/unisa.png'
  }

}
