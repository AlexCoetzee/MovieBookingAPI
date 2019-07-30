import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class TheatreService {
  constructor(private http: HttpClient) {}

  getAllTheatres(): Observable<any> {
    return this.http.get("http://localhost:8080/theatres");
  }

  getTheatreMoviesById(theatreId: Number): Observable<any> {
    console.log(theatreId);
    return this.http.get(
      "http://localhost:8080/theatres/" + theatreId + "/movies"
    );
  }
}
