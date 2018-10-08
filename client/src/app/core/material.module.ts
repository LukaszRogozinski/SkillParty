import {NgModule} from '@angular/core';
import {
  MatButtonModule, MatCardModule, MatInputModule, MatToolbarModule, MatProgressSpinnerModule, MatTableModule, MatDialogModule
} from '@angular/material';
import {CommonModule} from '@angular/common';

@NgModule({
  imports: [CommonModule, MatToolbarModule, MatButtonModule, MatCardModule, MatInputModule, MatProgressSpinnerModule, MatTableModule,
    MatDialogModule],
  exports: [CommonModule, MatToolbarModule, MatButtonModule, MatCardModule, MatInputModule, MatProgressSpinnerModule, MatTableModule,
    MatDialogModule],
})
export class CustomMaterialModule { }
