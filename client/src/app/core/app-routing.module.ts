import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from '../login/login.component';
import {NgModule} from '@angular/core';
import {WebsocketComponent} from '../websocket/websocket.component';
import {EventListComponent} from '../event/event-list/event-list.component';
import {EventDetailComponent} from '../event/event-detail/event-detail.component';
import {EventEditComponent} from '../event/event-edit/event-edit.component';
import {NewEventComponent} from '../event/new-event/new-event.component';
import {UserListComponent} from '../user/user-list/user-list.component';
import {UserDetailComponent} from '../user/user-detail/user-detail.component';
import {EventCategoryListComponent} from '../event-category/event-category-list/event-category-list.component';
import {RouteGuardComponent} from '../guards/routeGuard.component';
import {PageNotAvaliableComponent} from '../page-not-avaliable/page-not-avaliable.component';
import {LoginGuardComponent} from '../guards/loginGuard.component';
import {CanDeactivateGuard} from '../guards/can-deactivate-guard.service';
import {MyEventListComponent} from '../event/my-event-list/my-event-list.component';
import {HomeComponent} from '../home/home.component';
import {RegisterComponent} from '../login/register/register.component';
import {UserEditComponent} from '../user/user-edit/user-edit.component';

const routes: Routes = [
  {path: 'users', component: UserListComponent, canActivate: [RouteGuardComponent]},
  {path: 'login', component: LoginComponent, canActivate: [LoginGuardComponent]},
  {path : '', component : LoginComponent, pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'event', component: EventDetailComponent},
  {path: 'websocket', component: WebsocketComponent},
  {path: 'event-list', component: EventListComponent},
  {path: 'my-event-list', component: MyEventListComponent},
  {path: 'event-edit/:id', component: EventEditComponent},
  {path: 'new-event', component: NewEventComponent, canDeactivate: [CanDeactivateGuard]},
  {path: 'event-detail/:id', component: EventDetailComponent},
  {path: 'user-detail/:username', component: UserDetailComponent},
  {path: 'user-edit/:username', component: UserEditComponent},
  {path: 'event-category-list', component: EventCategoryListComponent},
  {path: 'pageNotAvaliable', component: PageNotAvaliableComponent, data: {message: 'Page not avaliable'}},
  {path: 'pageNotFound', component: PageNotAvaliableComponent, data: {message: 'Page not found'}},
  {path: 'register', component: RegisterComponent},
  {path: '**', redirectTo: '/pageNotFound'}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})
export class AppRoutingModule { }
