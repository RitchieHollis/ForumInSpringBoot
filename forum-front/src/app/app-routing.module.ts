import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { ChannelComponent } from './pages/channel/channel.component';
import { ChannelItemComponent } from './pages/channel-item/channel-item.component';

const routes: Routes = [
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "channel-list/:category",
    component: ChannelComponent
  },
  {
    path: "channel/:id",
    component: ChannelItemComponent
  },
  {
    path: "",
    redirectTo: "channel-list/GENERAL",
    pathMatch: "full"
  },
  {
    path: "**",
    redirectTo: "channel-list/GENERAL",
    pathMatch: "full"
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
