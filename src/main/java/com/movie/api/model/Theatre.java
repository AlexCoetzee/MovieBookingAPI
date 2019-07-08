package com.movie.api.model;

import java.util.ArrayList;
import java.util.List;

public class Theatre {
    private Integer theatreID;
    private String theatreName;
    private List<Auditorium> auditoriums;
    private List<Booking> bookings;

    public Theatre(Integer theatreID, String theatreName, List<Auditorium> auditoriums) {
        this.theatreID = theatreID;
        this.theatreName = theatreName;
        this.auditoriums = auditoriums;
        this.bookings = new ArrayList<>();
    }


    public Integer getTheatreID() {
        return theatreID;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public List<Auditorium> getAuditoriums() {
        return auditoriums;
    }


    public void saveBooking(Booking booking) {
        bookings.add(booking);
    }

    public List<Booking> getBookings() {
        return bookings;
    }
}
