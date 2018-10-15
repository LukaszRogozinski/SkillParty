import {RouterModule, Routes} from '@angular/router';
import {UserComponent} from '../user/user.component';
import {LoginComponent} from '../login/login.component';
import {NgModule} from '@angular/core';
import {EventComponent} from '../event/event.component';
import {WebsocketComponent} from '../websocket/websocket.component';

const routes: Routes = [
  { path: 'users', component: UserComponent },
  { path: 'login', component: LoginComponent },
  {path : '', component : LoginComponent},
  {path: 'event', component: EventComponent},
  {path: 'websocket', component: WebsocketComponent}
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
