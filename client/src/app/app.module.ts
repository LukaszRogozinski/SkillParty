import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppRoutingModule} from './core/app-routing.module';
import { LoginComponent } from './login/login.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {UserService} from './user/user.service';
import {Interceptor} from './core/inteceptor';
import {AuthService} from './core/auth.service';
import {TokenStorage} from './core/token.storage';
import { NavbarComponent } from './navbar/navbar.component';
import { WebsocketComponent } from './websocket/websocket.component';
import { EventListComponent } from './event/event-list/event-list.component';
import { EventDetailComponent } from './event/event-detail/event-detail.component';
import { EventEditComponent } from './event/event-edit/event-edit.component';
import {DropdownDirective} from './directives/dropdown.directive';
import { NewEventComponent } from './event/new-event/new-event.component';
import { HighlightDirective } from './directives/highlight.directive';
import { UserListComponent } from './user/user-list/user-list.component';
import { UserDetailComponent } from './user/user-detail/user-detail.component';
import { EventCategoryListComponent } from './event-category/event-category-list/event-category-list.component';
import { SimpleNotificationsModule } from 'angular2-notifications';
import {RouteGuardComponent} from './guards/routeGuard.component';
import { PageNotAvaliableComponent } from './page-not-avaliable/page-not-avaliable.component';
import {LoginGuardComponent} from './guards/loginGuard.component';
import {CanDeactivateGuard} from './guards/can-deactivate-guard.service';
import {CollapseModule, ModalModule} from 'ngx-bootstrap';
import {IsLoggedService} from './services/is-logged.service';
import { MyEventListComponent } from './event/my-event-list/my-event-list.component';
import { HomeComponent } from './home/home.component';
import { ConfirmDialogComponent } from './confirm-dialog/confirm-dialog.component';
import {MessageService} from './services/message.service';
import { RegisterComponent } from './login/register/register.component';
import { UserEditComponent } from './user/user-edit/user-edit.component';
import {HttpService} from './services/http.service';
import {HttpErrorHandler} from './services/http-error-handler.service';
import {Error400Component} from './error-pages/400/error-400.component';
import {Error401Component} from './error-pages/401/error-401.component';
import {Error403Component} from './error-pages/403/error-403.component';
import {Error404Component} from './error-pages/404/error-404.component';
import {Error500Component} from './error-pages/500/error-500.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavbarComponent,
    WebsocketComponent,
    EventListComponent,
    EventDetailComponent,
    EventEditComponent,
    DropdownDirective,
    NewEventComponent,
    HighlightDirective,
    UserListComponent,
    UserDetailComponent,
    EventCategoryListComponent,
    PageNotAvaliableComponent,
    MyEventListComponent,
    HomeComponent,
    ConfirmDialogComponent,
    RegisterComponent,
    UserEditComponent,
    Error400Component,
    Error401Component,
    Error403Component,
    Error404Component,
    Error500Component
  ],
  imports: [

    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    CollapseModule.forRoot(),
    SimpleNotificationsModule.forRoot(),
    ModalModule.forRoot()
  ],
  entryComponents: [ ConfirmDialogComponent],
  providers: [
    UserService,
    AuthService,
    TokenStorage,
    RouteGuardComponent,
    LoginGuardComponent,
    CanDeactivateGuard,
    IsLoggedService,
    MessageService,
    HttpService,
    HttpErrorHandler,
    {provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi : true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
