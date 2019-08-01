import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { TheatresComponent } from "./theatres/theatres.component";
import { SeatsComponent } from "./seats/seats.component";
import { PayConfirmationComponent } from "./pay-confirmation/pay-confirmation.component";
import { ScreeningComponent } from "./screening/screening.component";

const routes: Routes = [
  {
    path: "",
    component: TheatresComponent
  },
  {
    path: "screening",
    component: ScreeningComponent
  },
  {
    path: "seats",
    component: SeatsComponent
  },
  {
    path: "confirm",
    component: PayConfirmationComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
