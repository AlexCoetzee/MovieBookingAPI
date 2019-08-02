import { Component, OnInit } from "@angular/core";
import { BookingService } from "../services/booking.service";

@Component({
  selector: "app-pay-confirmation",
  templateUrl: "./pay-confirmation.component.html",
  styleUrls: ["./pay-confirmation.component.scss"]
})
export class PayConfirmationComponent implements OnInit {
  constructor(private bookingService: BookingService) {}

  ngOnInit() {}

  clearBookingDetails() {
    this.bookingService.clearBooking();
  }
}
