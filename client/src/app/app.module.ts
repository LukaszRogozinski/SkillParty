import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import {CustomMaterialModule} from './core/material.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppRoutingModule} from './core/app-routing.module';
import { LoginComponent } from './login/login.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {UserService} from './user/user.service';
import {Interceptor} from './core/inteceptor';
import {ErrorDialogComponent} from './core/error-dialog.component';
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
import {CollapseModule} from 'ngx-bootstrap';
import {IsLoggedService} from './services/is-logged.service';
import { MyEventListComponent } from './event/my-event-list/my-event-list.component';
import {IsLoggedUserEventService} from './services/is-logged-user-event.service';
import {CustomEventsService} from './services/custom-events.service';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ErrorDialogComponent,
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
    MyEventListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    CustomMaterialModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    CollapseModule.forRoot(),
    SimpleNotificationsModule.forRoot()
  ],
  entryComponents: [ErrorDialogComponent],
  providers: [
    ErrorDialogComponent,
    UserService,
    AuthService,
    TokenStorage,
    RouteGuardComponent,
    LoginGuardComponent,
    CanDeactivateGuard,
    CustomEventsService,
    IsLoggedService,
    IsLoggedUserEventService,
    {provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi : true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
