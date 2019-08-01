import { Component, OnInit } from "@angular/core";
import { ScreeningService } from "../services/screening.service";
import { Router, ActivatedRoute } from "@angular/router";
import { Time } from "@angular/common";
import { BookingService } from "../services/booking.service";

@Component({
  selector: "app-screening",
  templateUrl: "./screening.component.html",
  styleUrls: ["./screening.component.scss"]
})
export class ScreeningComponent implements OnInit {
  theatreId: number;
  movieId: number;
  screening = new Map<Date, Array<Time>>();
  date: Date;

  constructor(
    private screeningService: ScreeningService,
    private _router: Router,
    private route: ActivatedRoute,
    private bookingService: BookingService
  ) {}

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.theatreId = params["id"];
      this.movieId = params["movieId"];
      this.screeningService.test(this.movieId).subscribe(data => {
        data.responseBody.forEach(test => {
          if (this.screening.has(test.time.split(" ")[0])) {
            let blet = this.screening.get(test.time.split(" ")[0]);
            blet.push(test.time.split(" ")[1]);
            this.screening.set(test.time.split(" ")[0], blet);
          } else {
            let arr = new Array<Time>();
            arr.push(test.time.split(" ")[1]);
            this.screening.set(test.time.split(" ")[0], arr);
          }
        });
        console.log(this.screening);
      });
    });
  }

  test(time, key) {
    console.log(key);
    this._router.navigate(["/seats/"], {
      queryParams: {
        id: this.theatreId,
        movieId: this.movieId,
        date: key,
        time: time
      }
    });
    console.log(time);
  }

  setMovieDate(date) {
    this.bookingService.updateBookingDetail("movieDate", date);
  }

  setMovieTime(time) {
    this.bookingService.updateBookingDetail("movieTime", time);
  }
}
