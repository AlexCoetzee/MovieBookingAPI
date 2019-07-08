package com.movie.api.model;


public class Seat {
    private String seatID;
    private String seatInfo;

    public Seat(String seatID, String seatInfo) {
        this.seatID = seatID;
        this.seatInfo = seatInfo;
    }


    public String getSeatID() {
        return seatID;
    }

    public String getSeatInfo() {
        return seatInfo;
    }

    public void setSeatID(String seatID) {
        this.seatID = seatID;
    }

    public void setSeatInfo(String seatInfo) {
        this.seatInfo = seatInfo;
    }
}
