import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { TheatresComponent } from "./theatres/theatres.component";
import { HttpClientModule } from "@angular/common/http";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

import { MatSelectModule } from "@angular/material/select";
import { MatInputModule, MatIconModule } from "@angular/material";
import { MatButtonModule } from "@angular/material/button";
import { SeatsComponent } from "./seats/seats.component";
import { ScreeningComponent } from './screening/screening.component';
import {MatTabsModule} from '@angular/material/tabs';

@NgModule({
  declarations: [AppComponent, TheatresComponent, SeatsComponent, ScreeningComponent],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatSelectModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatTabsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
