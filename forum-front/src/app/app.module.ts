import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { ChannelComponent } from './pages/channel/channel.component';
import { NavigationComponent } from './core/navigation/navigation.component';
import { ChannelItemComponent } from './pages/channel-item/channel-item.component';
import { ChannelService } from './pages/channel/service/channel.service';
import { HttpClientModule } from '@angular/common/http';
import { UserComponent } from './pages/user/user.component';
import { PostComponent } from './pages/post/post.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ChannelComponent,
    NavigationComponent,
    ChannelItemComponent,
    UserComponent,
    PostComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    ChannelService,
    ChannelItemComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
