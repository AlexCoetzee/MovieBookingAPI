package com.movie.api.service;

import com.movie.api.db.Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CinemaService {

    public ArrayList<HashMap<String, String>> findAllCinemas() {
        return Database.selectAll("cinema");
    }

    public ArrayList<HashMap<String, String>> findCinemaById(String id) {
        return Database.selectById("cinema", id );
    }

    public ArrayList<HashMap<String, String>> findAllTheatre() {
        return Database.selectAll("theatre");
    }

    public ArrayList<HashMap<String, String>> findTheatreById(String id) {
        return Database.selectById("theatre", id );
    }

    public ArrayList<HashMap<String, String>> findAllMovies() {
        return Database.selectAll("movie");
    }

    public ArrayList<HashMap<String, String>> findMovieById(String id) {
        return Database.selectById("movie", id );
    }

    public ArrayList<HashMap<String, String>> findAllReservations() {
        return Database.selectAll("reservation");
    }

    public ArrayList<HashMap<String, String>> findReservationById(String id) {
        return Database.selectById("reservation", id );
    }

    public ArrayList<HashMap<String, String>> findAllScreenings() {
        return Database.selectAll("screening");
    }

    public ArrayList<HashMap<String, String>> findScreeningById(String id) {
        return Database.selectById("screening", id );
    }

    public ArrayList<HashMap<String, String>> findReservationByScreening(String id) {
        return Database.getReservationsByScreening( id );
    }

    public ArrayList<HashMap<String, String>> findSeatByCinema(String id) {
        return Database.findSeatByCinema( id );
    }

    public boolean deleteBooking(int id) {
        Database.deleteBooking(id);
        return true;
    }

    public boolean deleteCinema(int id) {
        Database.delete("cinema", id);
        return true;
    }

    public boolean deleteMovie(int id) {
        Database.delete("movie", id);
        return true;
    }

    public boolean deleteScreening(int id) {
        Database.delete("screening", id);
        return true;
    }

    public boolean deleteTheatre(int id) {
        Database.delete("theatre", id);
        return true;
    }

    public ArrayList<HashMap<String, String>> getMoviesByTheatre(String id) {
        ArrayList<HashMap<String, String>> movieIds = Database.findMovieByTheatre(id);
        ArrayList<HashMap<String, String>> movies= new ArrayList<HashMap<String, String>>();

        for (HashMap<String, String> movieId : movieIds) {
            movies.add(Database.selectById("movie",movieId.get("movie")).get(0));
        }
        return movies;
    }

    public ArrayList<HashMap<String, String>> getCinemaByMovieAndTheatre(int movieId, int theatreId) {
        return Database.getCinemaByMovieAndTheatre(movieId, theatreId);
    }

    public ArrayList<HashMap<String, String>> getScreeningByMovie(int movieId) {
        return Database.getScreeningByMovieId(movieId);
    }



    public ArrayList<HashMap<String, String>> getOccupiedSeats(int id) {
        return Database.getOccupiedSeatsByCinema(id);
    }

    public boolean bookSeat(int[] ids, int screening, String reservationName) {
        for (int id:ids) {
            if (Database.checkSeatOccupied(id)) {
                return false;
            }
        }
        
        for (int id:ids) {
            int seatReservationId =Database.bookSeat(screening, reservationName, id);
            if(seatReservationId == -1) {
                return false;
            }
        }
        return true;

//        if (!Database.checkSeatOccupied(id)) {
//            int seatReservationId =Database.bookSeat(screening, reservationName, seatId);
//            return seatReservationId;
//        }
//        return -1;
    }

}
