import {Injectable} from '@angular/core';
import {BsModalService} from 'ngx-bootstrap';
import {ConfirmDialogComponent} from '../confirm-dialog/confirm-dialog.component';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class MessageService {

  constructor(private modalService: BsModalService) {
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

}
