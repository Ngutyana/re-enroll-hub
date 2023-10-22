import {Component, ElementRef, Renderer2, AfterViewInit} from '@angular/core';
import {Router} from "@angular/router";
import {StudentService} from "../service/studentService";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements AfterViewInit {

  selectedTab: string = '';
  constructor(private renderer: Renderer2,
              private el: ElementRef,
              private router: Router,
              private studentService: StudentService) {}

  ngAfterViewInit() {
    const selectedTabElement = this.el.nativeElement.querySelector('.active');
    this.renderer.addClass(selectedTabElement, 'active-indicator');
  }

  selectTab(tab: string): void {
    this.selectedTab = tab;
  }

  get isSignedIn() {
    return this.studentService.isSignedIn();
  }

  toggleSign() {
    if(this.isSignedIn) {
      this.studentService.signOut();
      this.router.navigate([``]);
    } else {
      this.router.navigate([`login`]);
    }
  }
}
