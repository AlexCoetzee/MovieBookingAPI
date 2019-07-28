import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

export class CinemaService {
  constructor(private http: HttpClient) {}

  getCinemaByIdAndMovie(id, movieId): Observable<any> {
    return this.http.get(
      "http://localhost:8080/theatres/" + id + "/movies/" + movieId + "/cinema"
    );
  }
  getSeatByCinema(id): Observable<any> {
    return this.http.get("http://localhost:8080/cinemas/" + id + "/seats");
  }
  getOccupiedSeatByCinema(id): Observable<any> {
    return this.http.get(
      "http://localhost:8080/cinemas/" + id + "/occupiedSeats"
    );
  }
}
