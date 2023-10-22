import { Component, OnInit } from '@angular/core';
import {StudentService} from "../service/studentService";
import {Router} from "@angular/router";
import {Message} from "../model/message";

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  messages: Message[] = [];

  constructor(private studentService: StudentService, private router: Router) {
  }

  ngOnInit(): void {
    let m1 = new Message('I love you', 'Athi', new Date())
    let m2 = new Message('Where are you', 'Slii', new Date())
    let m3 = new Message('Not cool at all njayam', 'Unknown', new Date())
    let m4 = new Message('Sleep over tonight?', 'Admin', new Date())
    let m5 = new Message('Your application was rejected, fraud detected.', 'Sithole', new Date())

    this.messages.push(m1, m2, m3, m4, m5);
    if(!this.isSignedIn) {
      this.router.navigate([`login`]);
    }
  }

  get isSignedIn() {
    return this.studentService.isSignedIn();
  }

  getFormattedDate(date: Date) {
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Months are 0-indexed
    const year = date.getFullYear();

    return `${day}-${month}-${year}`;
  }

}
