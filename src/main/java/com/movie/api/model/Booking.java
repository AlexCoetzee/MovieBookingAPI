package com.movie.api.model;

public class Booking {

    private String bookingID;
    private Seat seat;
    private int theatreID;
    private int movieID;
    private int price;


    public Booking(String bookingID,  int theatreid, int movieid, Seat seat) {
        this.bookingID = bookingID;
        this.movieID = movieid;
        this.theatreID = theatreid;
        this.seat = seat;
        this.price = 90;
    }

    public String getBookingID() {
        return bookingID;
    }

    public Seat getSeat() {
        return seat;
    }

    public int getMovieID() { return movieID; }

    public int getTheatreID() { return theatreID; }

    public int getPrice() { return price; }
}
