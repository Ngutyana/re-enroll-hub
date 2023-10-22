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
    let m1 = new Message('Display Message from Admin', 'Yonela', new Date())
    let m2 = new Message('Can be multiple messages', 'Eyethu', new Date())
    let m3 = new Message('Will show who updated the messages and when', 'Sihle', new Date())

    this.messages.push(m1, m2, m3);
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
