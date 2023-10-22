import {Component, OnInit} from '@angular/core';
import {StudentService} from "../service/studentService";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";
import {HttpErrorResponse} from "@angular/common/http";
import {AppService} from "../service/appService";
import {Attachment} from "../model/attachment";
import {StudentInfo} from "../model/studentInfo";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  status: string = '';
  allowSubmission: boolean = true;

  applicationModel = {
    facultyOfStudy: "",
    qualification: "",
    yearOfQualification: '',
    yearOfDeregistration: 0,
    reasonOfDeregistration: "",
    storyBehindDeregistration: "",
    motivation: ""
  };

  attachments: Attachment[] = [];

  years: number[] = [2020, 2021, 2022, 2023];
  faculties: string[] = ["Informatics and Design", "Education"];
  reasons: string[] = ["Employment", "No Accommodation", "Other"];
  qualifications: string[] = ["Application Development", "Communication Networks", "Information Systems", "Multimedia Applications"];
  qualificationYears: string[] = ["1st year", "2nd year", "3rd year", "Advanced Diploma", "Post Graduate Diploma", "Masters", "PHD"];
  student: StudentInfo = new StudentInfo('','','','','','','','');

  constructor(private studentService: StudentService,
              private appService: AppService,
              private router: Router) {
  }

  ngOnInit(): void {
    if(!this.isSignedIn) {
      this.router.navigate([`login`]);
    } else {
      let studentInfo = this.studentService.getSignInUser();
      this.student = this.studentService.getSignInUser();
      if(studentInfo.applicationStatus !== 'NOT_SUBMITTED') {
        this.allowSubmission = false;
      }
      this.status = 'STATUS:  ' + studentInfo.applicationStatus;
    }
  }

  onFileSelected(event: any): void {
    let size = event.target.files.length;

    for(let i = 0; i < size; i++) {
      const reader = new FileReader();
      reader.onloadend = () => {
        if (reader.result instanceof ArrayBuffer) {
          this.attachments.push(new Attachment(event.target.files[i].type, event.target.files[i]));
        }
      };
      reader.readAsArrayBuffer(event.target.files[i] as Blob);

    }
    if(event.target.files.length === 0) {
      this.attachments = [];
    }

  }

  get isSignedIn() {
    return this.studentService.isSignedIn();
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

  public apply(form: NgForm): void {
    if (this.isValidApplicationForm()) {

      this.studentService.submitApplication(this.populateFormData())
        .subscribe(
          (res: StudentInfo) => {
            if (res) {
              this.studentService.setSignIn(res);
              alert("Application submitted")
              form.resetForm();
              this.allowSubmission = false;
              this.status = 'STATUS:  ' + res.applicationStatus;
            } else {
              alert("Application could not be submitted");
            }
          },
          (error: HttpErrorResponse) => {
            alert("Could not submit application");
          }
        );
    } else {
      alert("Please make sure all required application fields are provided");
    }
  }

  private populateFormData() {
    const formData = new FormData();
    for (const attachment of this.attachments) {
      formData.append('attachments', attachment.file);
    }
    formData.append('studentEmail', this.studentService.getSignInUser()?.email);
    formData.append('facultyOfStudy', this.applicationModel.facultyOfStudy);
    formData.append('qualification', this.applicationModel.qualification);
    formData.append('yearOfQualification', this.applicationModel.yearOfQualification);
    formData.append('yearOfDeregistration', this.applicationModel.yearOfDeregistration.toString());
    formData.append('reasonOfDeregistration', this.applicationModel.reasonOfDeregistration);
    formData.append('storyBehindDeregistration', this.applicationModel.storyBehindDeregistration);
    formData.append('motivation', this.applicationModel.motivation);
    return formData;
  }

  isValidApplicationForm() {
    return true;
  }

}
