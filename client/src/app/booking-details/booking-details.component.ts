import { Component, OnInit, OnDestroy } from "@angular/core";
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
  date: string;
  time: string;
  seats: Array<string>;
  seatNumber: number;
  ngOnInit() {
    this.getAvailableBookingDetails();
  }

  getAvailableBookingDetails() {
    this.cinema = this.bookingService.getBookingDetail("cinemaName");
    this.movie = this.bookingService.getBookingDetail("movieName");
    this.theatre = this.bookingService.getBookingDetail("theatreName");
    this.date = this.bookingService.getBookingDetail("movieDate");
    this.time = this.bookingService.getBookingDetail("movieTime");
    this.seats = this.bookingService.getBookingDetail("seats");
  }
}
