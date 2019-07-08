package com.movie.api.service;

import com.movie.api.db.MovieDB;
import com.movie.api.db.QueryDB;
import com.movie.api.model.*;

import java.util.ArrayList;
import java.util.List;

public class TheatreService {

    public List<OutputTheatre> findAllTheatres() {
        return mapOutputTheatre(MovieDB.getData());
    }

    public OutputTheatre findTheatreById(int id) {
        if (id <= findAllTheatres().size()) {
           return findAllTheatres().get(id - 1);
        }
       return null;
    }

    public List<Movie> findAllMovies(int theatreId) {
        List<Movie> movies = new ArrayList<>();
        List<Auditorium> auditoriums = QueryDB.findAllAuditoriumInATheatre(theatreId);
        auditoriums.forEach(item -> movies.add(item.getMovie()));
        return movies;
    }

    public List<Booking> findAllBookings(int theatreId) {
        List<Booking> bookingList = new ArrayList<>();
        Theatre theatres = QueryDB.findTheatreById(theatreId);
        theatres.getBookings().forEach(item -> bookingList.add(item));
        return bookingList;
    }

    public Movie findMovieById(int theatreId, int movieId) {
        List<Movie> movies = findAllMovies(theatreId);
        return movies.get(movieId - 1);
    }

    public AuditoriumSeats findSeats(int theatreId, int movieId) {
        List<Auditorium> auditoriums = QueryDB.findAllAuditoriumInATheatre(theatreId);
        Auditorium auditorium = auditoriums.get(movieId - 1);
        return auditorium.getAuditoriumSeats();
    }


    private List<OutputTheatre> mapOutputTheatre(List<Theatre> theatres) {
        List<OutputTheatre> outputTheatres = new ArrayList<>();
        theatres.forEach(theatre -> outputTheatres.add(new OutputTheatre(theatre.getTheatreID(), theatre.getTheatreName())));
        return outputTheatres;
    }

    public class OutputTheatre {
        private Integer theatreID;
        private String theatreName;

        public OutputTheatre(Integer theatreID, String theatreName) {
            this.theatreID = theatreID;
            this.theatreName = theatreName;
        }

        public Integer getTheatreID() {
            return theatreID;
        }

        public String getTheatreName() {
            return theatreName;
        }
    }
}
