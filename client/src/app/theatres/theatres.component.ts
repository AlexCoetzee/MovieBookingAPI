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

  chooseScreening(movieId) {
    console.log("MOVIE ID: " + movieId);
    this._router.navigate(["/screening/"], {
      queryParams: {
        id: this.theatreId,
        movieId: movieId
      }
    });
  }

  setCinema(cinemaName, cinemaId) {
    this.bookingService.updateBookingDetail("theatreName", cinemaName);
    this.bookingService.updateBookingDetail("theatreId", cinemaId);
  }

  setMovie(movieName, movieId) {
    this.bookingService.updateBookingDetail("movieName", movieName);
    this.bookingService.updateBookingDetail("movieId", movieId);
  }
}
