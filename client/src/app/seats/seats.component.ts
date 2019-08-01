import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { SeatModel } from "../models/seat.model";
import { SeatReservationModel } from "../models/seatReservation.model";
import { CinemaService } from "../services/cinema.service";
import { BookingService } from "../services/booking.service";
import { MatSnackBar } from "@angular/material";
import { FormControl, Validators } from "@angular/forms";

@Component({
  selector: "app-seats",
  templateUrl: "./seats.component.html",
  styleUrls: ["./seats.component.scss"]
})
export class SeatsComponent implements OnInit {
  cinema: Array<any>;
  movieId: number;
  theatreId: number;
  screeningId: number;

  allSeats: Array<SeatModel>;
  seats: Array<SeatModel>;
  availableSeats: Array<SeatModel>;
  occupiedSeats: Array<SeatReservationModel>;
  selectedSeats: Array<SeatModel>;
  seatIds: Array<number>;
  name: string;

  rowLetters: Array<String> = ["A", "B", "C", "D", "E"];

  seatsLayout: Array<Array<SeatModel>>;

  nameFormControl: FormControl;

  constructor(
    private cinemaService: CinemaService,
    private route: ActivatedRoute,
    private router: Router,
    private bookingService: BookingService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit() {
    this.nameFormControl = new FormControl("", [Validators.required]);

    this.selectedSeats = [];
    this.seatIds = [];
    this.route.queryParams.subscribe(params => {
      this.theatreId = params["id"];
      this.movieId = params["movieId"];
      this.screeningId = params["screeningId"];
      this.availableSeats = new Array<SeatModel>();
      this.findSeats();
    });
  }

  findSeats() {
    this.cinemaService
      .getCinemaByIdAndMovie(this.theatreId, this.movieId)
      .subscribe(data => {
        this.cinema = data.responseBody;

        this.cinemaService
          .getSeatByCinema(this.cinema[0].cinema)
          .subscribe(data => {
            this.allSeats = data.responseBody;

            this.buildSeatsLayoutArray();

            this.setSeats();
          });
      });
  }

  setSeats() {
    this.cinemaService
      .getOccupiedSeatByCinema(this.screeningId)
      .subscribe(data => {
        this.occupiedSeats = data.responseBody;

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
  }

  get getNumAvailableSeats() {
    if (this.availableSeats) {
      return this.availableSeats.length;
    }
  }

  get getTotalSeats() {
    if (this.allSeats) {
      return this.allSeats.length;
    }
  }

  get getTotalSelectedSeats() {
    if (this.selectedSeats) {
      return this.selectedSeats.length;
    }
  }

  get getTotalCost() {
    if (this.selectedSeats) {
      return this.selectedSeats.length * 100;
    }
  }

  buildSeatsLayoutArray() {
    let count = 1;
    this.seatsLayout = [];
    let row = [];
    for (let i = 1; i <= 5; i++) {
      row = [];
      for (let seat of this.allSeats) {
        if (seat.row == i) {
          row.push(seat);
        }
      }
      this.seatsLayout.push(row);
    }
    console.log(this.seatsLayout);
  }

  checkIfSeatIsSelected(id: Number): boolean {
    if (this.selectedSeats != undefined) {
      let seat = this.selectedSeats.find(e => e.Id === id);
      if (seat === undefined) {
        return false;
      } else {
        return true;
      }
    }
  }

  checkIfSeatIsOccupied(id: Number): boolean {
    if (this.occupiedSeats != undefined) {
      let seat = this.occupiedSeats.find(element => element.seat === id);
      if (seat === undefined) {
        return false;
      } else {
        return true;
      }
    }

    return false;
  }

  selectSeat(seat: SeatModel) {
    if (this.checkIfSeatIsSelected(seat.Id)) {
      let index = this.selectedSeats.indexOf(seat);
      this.selectedSeats.splice(index, 1);
    } else {
      if (!this.checkIfSeatIsOccupied(seat.Id)) {
        this.selectedSeats.push(seat);
      }
    }
  }

  confirmPay() {
    this.nameFormControl.markAsTouched();

    if (this.nameFormControl.valid) {
      this.name = this.nameFormControl.value;
    }

    if (this.selectedSeats.length > 0 && this.name && this.name !== "") {
      this.setSeatingDetails();

      console.log(this.bookingService.getBookingDetail("theatreId"));
      console.log(this.movieId);
      console.log(this.seatIds);
      console.log(this.screeningId);

      this.bookingService
        .makeBooking(
          this.bookingService.getBookingDetail("theatreId"),
          this.movieId,
          this.seatIds,
          this.screeningId,
          this.name
        )
        .subscribe(data => {
          console.log(data);
          if (data["responseStatus"] !== "CREATED") {
            this.snackBar.open(
              "An error has occured, please try again later",
              null,
              {
                duration: 2000,
                panelClass: ["alert-red"]
              }
            );
          } else {
            this.router.navigate(["/confirm"]);
          }
        });
    } else if (this.selectedSeats.length < 0) {
      this.snackBar.open("Please select at least one seat", null, {
        duration: 2000,
        panelClass: ["alert-red"]
      });
    } else {
    }
  }

  setSeatingDetails() {
    for (let i = 0; i < this.selectedSeats.length; i++) {
      let row = this.selectedSeats[i].row;
      let seatRowLetter = this.rowLetters[row - 1];
      let seatPosition =
        seatRowLetter + this.selectedSeats[i].number.toString();
      this.seatIds.push(this.selectedSeats[i].Id);
      this.bookingService.updateBookingDetail("seats", seatPosition);
    }
  }
}
