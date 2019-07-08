package com.movie.api.model;

import java.util.List;

public class AuditoriumSeats {

    private List<Seat> seats;

    public AuditoriumSeats(List<Seat> chairs) {
        this.seats = chairs;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}
