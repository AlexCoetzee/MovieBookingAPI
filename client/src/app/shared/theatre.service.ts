import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TheatreService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get('http://localhost:8080/theatres');
  }

  getTheatreMoviesById(id): Observable<any> {
    console.log(id);
    return this.http.get('http://localhost:8080/theatres/' + id + '/movies');
  }

  getCinemaByIdAndMovie(id, movieId): Observable<any> {
    return this.http.get('http://localhost:8080/theatres/' + id + '/movies/' + movieId + '/cinema');
  }
  getSeatByCinema(id): Observable<any> {
    return this.http.get('http://localhost:8080/cinemas/' + id + '/seats');
  }
  getOccupiedSeatByCinema(id): Observable<any> {
    return this.http.get('http://localhost:8080/cinemas/' + id + '/occupiedSeats');
  }
}
