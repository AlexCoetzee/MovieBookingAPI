import { Component, OnInit } from "@angular/core";
import { BookingService } from "../services/booking.service";

@Component({
  selector: "app-booking-details",
  templateUrl: "./booking-details.component.html",
  styleUrls: ["./booking-details.component.scss"]
})
export class BookingDetailsComponent implements OnInit {
  constructor(private bookingService: BookingService) {}

  movie: string;
  cinema: string;
  theatre: string;
  time: string;
  seats: Array<String>;
  seatNumber: Number;
  ngOnInit() {
    this.getAvailableBookingDetails();
  }

  getAvailableBookingDetails() {
    this.cinema = this.bookingService.getBookingDetail("cinemaName");
    this.movie = this.bookingService.getBookingDetail("movieName");
    this.theatre = this.bookingService.getBookingDetail("theatreName");
    this.time = this.bookingService.getBookingDetail("movieTime");
    this.seats = this.bookingService.getBookingDetail("seats");
  }
}
