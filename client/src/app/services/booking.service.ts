import { Injectable } from "@angular/core";
import { BookingDetails } from "../models/booking-details.model";
import { HttpHeaders, HttpClient } from "@angular/common/http";
import { map } from "rxjs/operators";

@Injectable({
  providedIn: "root"
})
export class BookingService {
  constructor(private httpClient: HttpClient) {}
  bookingDetails: BookingDetails = {
    movieName: null,
    movieId: null,
    cinemaName: null,
    cinemaId: null,
    theatreName: null,
    theatreId: null,
    screeningId: null,
    movieDate: null,
    movieTime: null,
    seats: [],
    seatIds: [],
    name: null
  };

  updateBookingDetail(detail, value) {
    if (detail === "seats" || detail == "seatIds") {
      this.bookingDetails[detail].push(value);
    } else {
      this.bookingDetails[detail] = value;
    }
  }

  getBookingDetail(detail) {
    return this.bookingDetails[detail];
  }

  makeBooking(theatreId, movieId, seatId: Number[], screeningId, name) {
    console.log("called");
    let bookingInfo = {
      seatId: seatId,
      screeningId: screeningId,
      name: name
    };

    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json"
      })
    };
    return this.httpClient.post(
      "http://localhost:8080/theatres/" +
        theatreId +
        "/movies/" +
        movieId +
        "/book-a-seat",
      bookingInfo
    );
  }

  clearBooking() {
    this.bookingDetails.movieName = null;
    this.bookingDetails.movieId = null;
    this.bookingDetails.cinemaName = null;
    this.bookingDetails.cinemaId = null;
    this.bookingDetails.theatreName = null;
    this.bookingDetails.theatreId = null;
    this.bookingDetails.screeningId = null;
    this.bookingDetails.movieDate = null;
    this.bookingDetails.movieTime = null;
    this.bookingDetails.name = null;
    this.bookingDetails.seats = [];
    this.bookingDetails.seatIds = [];
  }
}
