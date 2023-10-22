import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MatButtonModule } from "@angular/material/button";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";
import { LandingComponent } from './landing/landing.component';
import {MatListModule} from "@angular/material/list";
import {CookieService} from "ngx-cookie-service";
import {MatTableModule} from "@angular/material/table";
import {MatSelectModule} from "@angular/material/select";
import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './home/home.component';
import {RegisterComponent} from "./register/register.component";
import { MessageComponent } from './message/message.component';
import { SettingsComponent } from './settings/settings.component';
import { ProgressComponent } from './progress/progress.component';

@NgModule({
  declarations: [
    AppComponent, LandingComponent, NavbarComponent, HomeComponent, RegisterComponent, MessageComponent, SettingsComponent, ProgressComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        MatButtonModule,
        HttpClientModule,
        FormsModule,
        MatListModule,
        MatTableModule,
        MatSelectModule
    ],
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
