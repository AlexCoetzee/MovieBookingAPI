package com.movie.api.model;

import lombok.Getter;

@Getter
public class Booking {

    private int[] seatId;
    private int screeningId;
    private String name;


    public Booking( int[] seat, int screeningId, String name) {
        this.seatId = seat;
        this.screeningId = screeningId;
        this.name = name;
    }
}
