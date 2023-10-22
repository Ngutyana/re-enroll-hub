import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {StudentInfo} from "../model/studentInfo";
import {StudentRegistrationForm} from "../model/studentRegistrationForm";

@Injectable({
  providedIn: "root"
})
export class  StudentService {

  private baseUrl = "http://localhost:8080/student";
  private studentSignedIn: StudentInfo = this.clearStudentDetails() ;

  constructor(private http: HttpClient) { }

  public loginStudent(email: string, password: string): Observable<StudentInfo> {
    return this.http.get<StudentInfo>(`${this.baseUrl}/login/${email}/${password}`);
  }

  public registerStudent(student: StudentRegistrationForm): Observable<StudentInfo> {
    return this.http.post<StudentInfo>(`${this.baseUrl}/save`, student);
  }

  public submitApplication(application: FormData): Observable<StudentInfo> {
    return this.http.post<StudentInfo>(`${this.baseUrl}/application`, application);
  }

  public cancelApplication(studentEmail: string): Observable<StudentInfo> {
    return this.http.get<StudentInfo>(`${this.baseUrl}/cancel`,
      {params: {"studentEmail": studentEmail}});
  }

  public updateStudent(studentEmail: string, altEmail: string, contactNumber: string, address: string) {
    return this.http.get<StudentInfo>(`${this.baseUrl}/update`,
      {params: {"studentEmail": studentEmail, "altEmail": altEmail, "contactNumber": contactNumber, "address": address}});
  }

  public isSignedIn() {
    return this.studentSignedIn.name !== '' &&
      this.studentSignedIn.email !== '';
  }

  setSignIn(studentInfo: StudentInfo) {
    this.studentSignedIn = studentInfo;
  }

  getSignInUser() {
    return this.studentSignedIn;
  }

  signOut() {
    this.studentSignedIn = this.clearStudentDetails();
  }

  private clearStudentDetails() {
    return new StudentInfo('','','','','','','', '');
  }

}

