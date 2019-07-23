//package com.movie.api.db;
//
//import com.movie.api.model.*;
//import com.movie.api.model.enums.SEAT_INFO;
//
//import java.util.List;
//
//public class QueryDB {
//
//    private QueryDB() {}
//
//    public static Seat findASeatByIdFromAuditorium(AuditoriumSeats auditoriumSeats, Seat s) {
//        List<Seat> seatList = auditoriumSeats.getSeats();
//
//        for (Seat seat: seatList) {
//            if (s.getSeatID().equals(seat.getSeatID())) {
//                return seat;
//            }
//        }
//        return null;
//    }
//
//    public static Theatre findTheatreById(int theatre) {
//        int i = theatre - 1;
//        return MovieDB.getData().get(i);
//    }
//
//    public static Auditorium findAuditoriumById(int theatreId, int movieId) {
//        return MovieDB.getData().get(theatreId - 1).getAuditoriums().get(movieId - 1);
//    }
//
//    public static boolean updateSeatStatus(int theatreId, int movieId, Seat seat, String seatStatus) {
//        Auditorium auditorium = findAuditoriumById(theatreId, movieId);
//        AuditoriumSeats auditoriumSeats = auditorium.getAuditoriumSeats();
//        for(Seat s: auditoriumSeats.getSeats()) {
//            if(s.getSeatID().equals(seat.getSeatID())) {
//                s.setSeatInfo(seatStatus);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static boolean resetSeatStatus(int theatreId, String bookingId) {
//        Booking booking = QueryDB.findABookingById(theatreId, bookingId);
//        if (booking != null) {
//            Auditorium auditorium = findAuditoriumById(theatreId, booking.getMovieID());
//            AuditoriumSeats auditoriumSeats = auditorium.getAuditoriumSeats();
//            Seat seat = QueryDB.findASeatByIdFromAuditorium(auditoriumSeats, booking.getSeat());
//            seat.setSeatInfo(SEAT_INFO.AVAILABLE.name());
//            return true;
//        }
//        return false;
//    }
//
//
//    public static List<Auditorium> findAllAuditoriumInATheatre(int theatreId) {
//        return MovieDB.getData().get(theatreId - 1).getAuditoriums();
//    }
//
//    public static Booking findABookingById(int theatreId, String bookingId) {
//        Booking booking = null;
//        List<Booking> bookingList = MovieDB.getData().get(theatreId - 1).getBookings();
//        for (Booking bookingItem: bookingList) {
//            if(bookingItem.getBookingID().equals(bookingId)){
//                booking = bookingItem;
//            }
//        }
//        return booking;
//    }
//
//    public static void deleteABooking(int theatreId, String bookingId) {
//        List<Booking> bookingList = MovieDB.getData().get(theatreId - 1).getBookings();
//        for (Booking bookingItem: bookingList) {
//            if(bookingItem.getBookingID().equals(bookingId)){
//                bookingList.remove(bookingItem);
//                break;
//            }
//        }
//    }
//
//}
