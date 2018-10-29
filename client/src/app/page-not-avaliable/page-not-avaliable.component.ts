import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-page-not-avaliable',
  templateUrl: './page-not-avaliable.component.html',
  styleUrls: ['./page-not-avaliable.component.css']
})
export class PageNotAvaliableComponent implements OnInit {

  errorMessage: string;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.errorMessage = this.route.snapshot.data['message'];
  }

}
