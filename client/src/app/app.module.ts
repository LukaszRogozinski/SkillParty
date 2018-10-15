import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import {CustomMaterialModule} from './core/material.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { UserComponent } from './user/user.component';
import {AppRoutingModule} from './core/app-routing.module';
import { LoginComponent } from './login/login.component';
import {FormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {UserService} from './user/user.service';
import {Interceptor} from './core/inteceptor';
import {ErrorDialogComponent} from './core/error-dialog.component';
import {AuthService} from './core/auth.service';
import {TokenStorage} from './core/token.storage';
import { EventComponent } from './event/event.component';
import { NavbarComponent } from './navbar/navbar.component';
import { WebsocketComponent } from './websocket/websocket.component';

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    LoginComponent,
    ErrorDialogComponent,
    EventComponent,
    NavbarComponent,
    WebsocketComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    CustomMaterialModule,
    FormsModule,
    AppRoutingModule
  ],
  entryComponents: [ErrorDialogComponent],
  providers: [
    ErrorDialogComponent,
    UserService,
    AuthService,
    TokenStorage,
    {provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi : true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
