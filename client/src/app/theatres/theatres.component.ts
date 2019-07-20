import { Component, OnInit } from '@angular/core';
import { TheatreService } from '../shared/theatre.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-theatres',
  templateUrl: './theatres.component.html',
  styleUrls: ['./theatres.component.scss']
})
export class TheatresComponent implements OnInit {

  theatres: Array<any>;
  theatresMovies: Array<any>;
  theatreId: number;

  constructor(private theatreService: TheatreService, private _router: Router) { }

  ngOnInit() {
    this.theatreService.getAll().subscribe(data => {
      this.theatres = data["responseBody"];
      console.log(this.theatres)
    })
  }

  getMovies(theatreId) {
    this.theatreId = theatreId;
    this.theatreService.getTheatreMoviesById(theatreId).subscribe(data => {
      this.theatresMovies = data["responseBody"];
      console.log(this.theatresMovies)
    })
  }

  chooseSeats(movieId) {
    this._router.navigate(["/seats"], {
      queryParams: {
        id: this.theatreId,
        movie: movieId
      }
    })
  }

}
