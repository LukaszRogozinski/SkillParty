import {Component, OnInit} from '@angular/core';
import {TokenStorage} from '../core/token.storage';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  username: string;

  constructor(private tokenStorage: TokenStorage) {
  }

  ngOnInit() {
    this.username = this.tokenStorage.getUsernameFromToken();
  }

}
