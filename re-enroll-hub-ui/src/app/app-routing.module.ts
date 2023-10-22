import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LandingComponent} from "./landing/landing.component";
import {HomeComponent} from "./home/home.component";
import {RegisterComponent} from "./register/register.component";
import {MessageComponent} from "./message/message.component";
import {SettingsComponent} from "./settings/settings.component";
import {ProgressComponent} from "./progress/progress.component";

const routes: Routes = [
  {path: '', component: LandingComponent},
  {path: 'home', component: HomeComponent},
  {path: 'login', component: RegisterComponent},
  {path: 'messages', component: MessageComponent},
  {path: 'progress', component: ProgressComponent},
  {path: 'settings', component: SettingsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
