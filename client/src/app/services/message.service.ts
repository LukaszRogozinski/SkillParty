import {Injectable, OnInit} from '@angular/core';
import {BsModalService} from 'ngx-bootstrap';
import {ConfirmDialogComponent} from '../confirm-dialog/confirm-dialog.component';
import {Observable} from 'rxjs/Observable';
import {NotificationsService, NotificationType} from 'angular2-notifications';
import {FormBuilder, FormGroup} from '@angular/forms';

@Injectable()
export class MessageService implements OnInit{

  form: FormGroup;

  constructor(private modalService: BsModalService,
              private _notifications: NotificationsService,
              private _fb: FormBuilder) {
  }

  ngOnInit(){//to co poniżej można zostawić jeśli chcemy customizować swoje powiadomienie ale musi być wywołane w każdej metodzie osobno
/*    this.form = this._fb.group({
      timeOut: 5000,
      showProgressBar: true,
      pauseOnHover: true,
      clickToClose: true,
      animate: 'fromRight'
    });*/
  }

  confirm(title?: string, message?: string, confirm?: string, decline?: string): Observable<boolean> {
    const initialState = {
      title: title,
      message: message,
      confirm: confirm,
      decline: decline
    };
    const bsModalRef = this.modalService.show(ConfirmDialogComponent,
      {animated: true, keyboard: true, backdrop: true, ignoreBackdropClick: true, initialState: initialState});

    return bsModalRef.content.confirmed;
  }

  createNotification(message: string){

    let messageBody : {title: string, body: string, type: string};
    messageBody = JSON.parse(message);
    const title = messageBody.title;
    const content = messageBody.body;
    const type = NotificationType[messageBody.type];
    this._notifications.create(title, content, type);
  }

  success(message: string){
    this._notifications.create("Success", message, NotificationType.Success);
  }

  alert(message: string){
    this._notifications.create("Alert", message, NotificationType.Alert);
  }

  error(message: string){
    this._notifications.create("Error", message, NotificationType.Error);
  }

  info(message: string){
    this._notifications.create("Info", message, NotificationType.Info);
  }

  warn(message: string){
    this._notifications.create("Warn", message, NotificationType.Warn);
  }
}
