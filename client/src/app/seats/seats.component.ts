import { Component, OnInit } from '@angular/core';
import { TheatreService } from '../shared/theatre.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-seats',
  templateUrl: './seats.component.html',
  styleUrls: ['./seats.component.scss']
})
export class SeatsComponent implements OnInit {

  seats: Array<any>;
  constructor(private theatreService: TheatreService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.theatreService.getTheatreSeatsByIdAndMovie(1, 1).subscribe(data => {
        this.seats = data["responseBody"];
        console.log(data);
        console.log(this.seats);
      })
    })
  }
}
