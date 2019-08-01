import { Component, OnInit } from "@angular/core";
import { TheatreService } from "../services/theatre.service";
import { Router } from "@angular/router";
import { BookingService } from "../services/booking.service";

@Component({
  selector: "app-theatres",
  templateUrl: "./theatres.component.html",
  styleUrls: ["./theatres.component.scss"]
})
export class TheatresComponent implements OnInit {
  theatres: Array<any>;
  theatresMovies: Array<any>;
  theatreId: number;

  constructor(
    private theatreService: TheatreService,
    private _router: Router,
    private bookingService: BookingService
  ) {}

  ngOnInit() {
    this.theatreService.getAllTheatres().subscribe(data => {
      this.theatres = data["responseBody"];
      console.log(this.theatres);
    });
  }

  getMovies(theatreId) {
    this.theatreId = theatreId;
    console.log(this.theatreId);
    this.theatreService.getTheatreMoviesById(theatreId).subscribe(data => {
      this.theatresMovies = data["responseBody"];
      console.log(data);
      console.log(this.theatresMovies);
    });
  }

  chooseSeats(movieId) {
    console.log("MOVIE ID: " + movieId);
    this._router.navigate(["/seats/"], {
      queryParams: {
        id: this.theatreId,
        movie: movieId
      }
    });
  }

  setCinema(cinemaName) {
    this.bookingService.updateBookingDetail("cinemaName", cinemaName);
  }

  setMovie(movieName) {
    this.bookingService.updateBookingDetail("movieName", movieName);
  }
}
