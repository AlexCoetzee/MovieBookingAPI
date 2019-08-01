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
import { PayConfirmationComponent } from './pay-confirmation/pay-confirmation.component';
import { BookingDetailsComponent } from './booking-details/booking-details.component';

@NgModule({
  declarations: [AppComponent, TheatresComponent, SeatsComponent, PayConfirmationComponent, BookingDetailsComponent],
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
    MatIconModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
