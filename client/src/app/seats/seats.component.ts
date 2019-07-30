import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { SeatModel } from "../models/seat.model";
import { SeatReservationModel } from "../models/seatReservation.model";
import { CinemaService } from "../services/cinema.service";

@Component({
  selector: "app-seats",
  templateUrl: "./seats.component.html",
  styleUrls: ["./seats.component.scss"]
})
export class SeatsComponent implements OnInit {
  cinema: Array<any>;
  movieId: number;
  theatreId: number;

  allSeats: Array<SeatModel>;
  seats: Array<SeatModel>;
  availableSeats: Array<SeatModel>;
  occupiedSeats: Array<SeatReservationModel>;

  constructor(
    private cinemaService: CinemaService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.theatreId = params["id"];
      this.movieId = params["movie"];
      this.availableSeats = new Array<SeatModel>();
      this.findSeats();
    });
  }

  findSeats() {
    this.cinemaService
      .getCinemaByIdAndMovie(this.theatreId, this.movieId)
      .subscribe(data => {
        this.cinema = data.responseBody;
        console.log(this.cinema);

        this.cinemaService
          .getSeatByCinema(this.cinema[0].cinema)
          .subscribe(data => {
            this.allSeats = data.responseBody;
            console.log("ALL SEATS: ");
            console.log(  this.allSeats);
          });

        this.cinemaService
          .getOccupiedSeatByCinema(this.cinema[0].cinema)
          .subscribe(data => {
            this.occupiedSeats = data.responseBody;
            console.log(this.occupiedSeats);

            this.allSeats.forEach(a => {
              let isInArray = false;
              this.occupiedSeats.forEach(b => {
                if (a.Id === b.seat) {
                  isInArray = true;
                }
              });
              if (!isInArray) {
                this.availableSeats.push(a);
              }
            });
          });
      });
  }

  getNumAvailableSeats() {
    return this.availableSeats.length;
  }

  getTotalSeats() {
    return this.allSeats.length;
  }
}
