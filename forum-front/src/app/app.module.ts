import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { ChannelComponent } from './pages/channel/channel.component';
import { NavigationComponent } from './core/navigation/navigation.component';
import { ChannelItemComponent } from './pages/channel-item/channel-item.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ChannelComponent,
    NavigationComponent,
    ChannelItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
