import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: "root"
})
export class CinemaService {
  constructor(private http: HttpClient) {}

  getCinemaByIdAndMovie(theatreId: Number, movieId: Number): Observable<any> {
    return this.http.get(
      "http://localhost:8080/theatres/" +
        theatreId +
        "/movies/" +
        movieId +
        "/cinema"
    );
  }
  getSelectedCinemaId() {}
  getSeatByCinema(cinemaId: Number): Observable<any> {
    return this.http.get(
      "http://localhost:8080/cinemas/" + cinemaId + "/seats"
    );
  }
  getOccupiedSeatByCinema(cinemaId: Number): Observable<any> {
    return this.http.get(
      "http://localhost:8080/cinemas/" + cinemaId + "/occupiedSeats"
    );
  }
}
