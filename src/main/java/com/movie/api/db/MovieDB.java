//package com.movie.api.db;
//
//import com.movie.api.model.*;
//import com.movie.api.model.enums.SEAT_INFO;
//
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class MovieDB {
//
//    private static List<Theatre> theatreList;
//
//    static {
//        theatreList = generateTheatres();
//    }
//    private MovieDB() {}
//
//    public static List<Theatre> getData() {
//
//        return theatreList;
//    }
//
//
//    private static AuditoriumSeats getSeats() {
//        List<Seat> seats = new ArrayList<>();
//        seats.add(new Seat("A-1", SEAT_INFO.AVAILABLE.name()));
//        seats.add(new Seat("A-2", SEAT_INFO.AVAILABLE.name()));
//        seats.add(new Seat("A-3", SEAT_INFO.AVAILABLE.name()));
//        seats.add(new Seat("B-1", SEAT_INFO.AVAILABLE.name()));
//        seats.add(new Seat("B-2", SEAT_INFO.AVAILABLE.name()));
//        seats.add(new Seat("B-3", SEAT_INFO.AVAILABLE.name()));
//        seats.add(new Seat("C-1", SEAT_INFO.AVAILABLE.name()));
//        seats.add(new Seat("C-2", SEAT_INFO.AVAILABLE.name()));
//        seats.add(new Seat("C-3", SEAT_INFO.AVAILABLE.name()));
//        return new AuditoriumSeats(seats);
//    }
//
//    private static List<Movie> getMovies() {
//        List<Movie> movies = new ArrayList<>();
//        movies.add(new Movie(1, "Fast & Furious Presents: Hobbs & Shaw", Date.valueOf("2019-01-01")));
//        movies.add(new Movie(2, "Angel Has Fallen",  Date.valueOf("2019-01-01")));
//        movies.add(new Movie(3, "Rambo: Last Blood",  Date.valueOf("2019-01-01")));
//        movies.add(new Movie(4, "The Art of Racing in the Rain",  Date.valueOf("2019-01-01")));
//        movies.add(new Movie(5, "Terminator: Dark Fate",  Date.valueOf("2019-01-01")));
//        movies.add(new Movie(6, "Zombieland: Double Tap",  Date.valueOf("2019-01-01")));
//        return movies;
//    }
//
//    private static List<Auditorium> createAuditorium() {
//        List<Auditorium> auditoriums = new ArrayList<>();
//        auditoriums.add(new Auditorium(1, getMovies().get(0), getSeats()));
//        auditoriums.add(new Auditorium(2, getMovies().get(1), getSeats()));
//        auditoriums.add(new Auditorium(3, getMovies().get(2), getSeats()));
//        auditoriums.add(new Auditorium(4, getMovies().get(3), getSeats()));
//        auditoriums.add(new Auditorium(5, getMovies().get(4), getSeats()));
//        auditoriums.add(new Auditorium(6, getMovies().get(5), getSeats()));
//        return auditoriums;
//    }
//
//    private static List<Theatre> generateTheatres() {
//        List<Theatre> theatres = new ArrayList<>();
//        theatres.add(new Theatre(1, "Ster-Kinekor Eastgate", createAuditorium()));
//        theatres.add(new Theatre(2, "Nu Metro The Glen", createAuditorium()));
//        theatres.add(new Theatre(3, "Nu Metro Emperors Palace", createAuditorium()));
//        return theatres;
//    }
//
//}
