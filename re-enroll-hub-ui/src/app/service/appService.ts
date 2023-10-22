import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({providedIn: "root"})
export class AppService {

  private baseUrl = "http://localhost:8080/api";

  constructor(private http: HttpClient) { }

  public loadUniversities(): Observable<String[]> {
    return this.http.get<String[]>(`${this.baseUrl}/universities`);
  }

}
