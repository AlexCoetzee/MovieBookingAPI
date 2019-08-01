import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ScreeningService {

  constructor(private http: HttpClient) { }

  test(movieId: number): Observable<any> {
    console.log(1);
    return this.http.get(
      "http://localhost:8080/screenings/movies/" + movieId
    );
  }
}
