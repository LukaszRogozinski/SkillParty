import {RouterModule, Routes} from '@angular/router';
import {UserComponent} from '../user/user.component';
import {LoginComponent} from '../login/login.component';
import {NgModule} from '@angular/core';
import {WebsocketComponent} from '../websocket/websocket.component';
import {EventListComponent} from '../event/event-list/event-list.component';
import {EventDetailComponent} from '../event/event-detail/event-detail.component';
import {EventEditComponent} from '../event/event-edit/event-edit.component';
import {NewEventComponent} from '../event/new-event/new-event.component';

const routes: Routes = [
  { path: 'users', component: UserComponent },
  { path: 'login', component: LoginComponent },
  {path : '', component : LoginComponent},
  {path: 'home', component: EventListComponent},
  {path: 'event', component: EventDetailComponent},
  {path: 'websocket', component: WebsocketComponent},
  {path: 'event-list', component: EventListComponent},
  {path: 'event-edit', component: EventEditComponent},
  {path: 'new-event', component: NewEventComponent},
  {path: 'event-detail/:id', component: EventDetailComponent}
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
