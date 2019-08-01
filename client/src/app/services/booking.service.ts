import { Injectable } from "@angular/core";
import { BookingDetails } from "../models/booking-details.model";

@Injectable({
  providedIn: "root"
})
export class BookingService {
  constructor() {}
  bookingDetails: BookingDetails = {
    movieName: null,
    cinemaName: null,
    theatreName: null,
    movieTime: null,
    seats: []
  };

  updateBookingDetail(detail, value) {
    if (detail === "seats") {
      this.bookingDetails[detail].push(value);
    } else {
      this.bookingDetails[detail] = value;
    }
  }

  getBookingDetail(detail) {
    return this.bookingDetails[detail];
  }

  clearBooking() {
    this.bookingDetails.movieName = null;
    this.bookingDetails.cinemaName = null;
    this.bookingDetails.theatreName = null;
    this.bookingDetails.movieTime = null;
    this.bookingDetails.seats = null;
  }
}
