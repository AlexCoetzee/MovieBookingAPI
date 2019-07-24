import { Component, OnInit } from '@angular/core';
import { TheatreService } from '../shared/theatre.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-seats',
  templateUrl: './seats.component.html',
  styleUrls: ['./seats.component.scss']
})
export class SeatsComponent implements OnInit {

  cinema: Array<any>;
  // All seats in the cinema
  allSeats: Array<any>;
  // All unoccupied seats
  seats: Array<any>;
  // All occupied seats
  occupiedSeats: Array<any>;
  constructor(private theatreService: TheatreService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.seats = new Array<any>();
      this.findSeats();
    });
  }

  get showAvailableSeat() {
    if (this.allSeats === undefined || this.occupiedSeats === undefined) {
      return false;
    }
    this.allSeats.forEach(a => {
      this.occupiedSeats.forEach(b => {
        if (a.Id === b.seat) {
          return false;
        }
      });
    });
    return true;
  }

  async findSeats() {
    this.theatreService.getCinemaByIdAndMovie(1, 1).subscribe(data => {
      this.cinema = data['responseBody'];
      console.log(this.cinema);
      this.theatreService.getSeatByCinema(this.cinema[0].cinema).subscribe(data => {
        this.allSeats = data['responseBody'];
        console.log(this.allSeats);
      });
      this.theatreService.getOccupiedSeatByCinema(this.cinema[0].cinema).subscribe(data => {
        this.occupiedSeats = data['responseBody'];
        console.log(this.occupiedSeats);

        this.allSeats.forEach(a => {
          let isInArray = false
          this.occupiedSeats.forEach(b => {
            if (a.Id === b.seat) {
              isInArray = true;
            }
          });
          if (!isInArray) {
            this.seats.push(a);
          }
          
        });
      });
    });
  }
}
