package com.movie.api.model;

public class Auditorium {
    private Integer auditoriumID;
    private Movie movie;
    private AuditoriumSeats seats;

    public Auditorium(Integer auditoriumID, Movie movie, AuditoriumSeats seats) {
        this.auditoriumID = auditoriumID;
        this.movie = movie;
        this.seats = seats;
    }

    public Integer getAuditoriumID() {
        return auditoriumID;
    }

    public Movie getMovie() {
        return movie;
    }

    public AuditoriumSeats getAuditoriumSeats() {
        return seats;
    }
}
