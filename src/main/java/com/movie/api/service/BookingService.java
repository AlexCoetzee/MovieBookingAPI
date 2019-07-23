//package com.movie.api.service;
//
//import com.movie.api.db.QueryDB;
//import com.movie.api.model.AuditoriumSeats;
//import com.movie.api.model.Booking;
//import com.movie.api.model.Payment;
//import com.movie.api.model.Seat;
//import com.movie.api.model.enums.SEAT_INFO;
//import org.springframework.stereotype.Service;
//
//import java.nio.ByteBuffer;
//import java.util.UUID;
//
//@Service
//public class BookingService {
//
//
//
//    private static Booking createABooking(int theatreid, int movieid, Seat seat) {
//        return new Booking(shortUUID(),theatreid,  movieid, seat);
//    }
//
//    private static String shortUUID() {
//        UUID uuid = UUID.randomUUID();
//        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
//        return Long.toString(l, Character.MAX_RADIX);
//    }
//
//    private static boolean isSeatAvailable(Seat seat) {
//        return seat.getSeatInfo().equals(SEAT_INFO.AVAILABLE.name());
//    }
//
//    private static boolean isSeatBooked(Seat seat) {
//        return seat.getSeatInfo().equals(SEAT_INFO.BOOKED.name());
//    }
//
//    private static boolean isSeatIdValid(Seat seat) {
//        return seat != null;
//    }
//
//    private static Seat findASeat(int theatreId, int movieId, Seat seat) {
//        AuditoriumSeats auditoriumSeats = QueryDB.findAuditoriumById(theatreId,movieId).getAuditoriumSeats();
//        return QueryDB.findASeatByIdFromAuditorium(auditoriumSeats, seat);
//    }
//
//    private boolean validateCardDetails(Payment cardDetails) {
//        if (cardDetails.getMaskedCardNumber().length() != 12
//                || cardDetails.getCardExpiryMonth().length() > 2
//                || cardDetails.getCardExpiryYear().length() > 2
//                || cardDetails.getCardCVVNumber().length() != 3) {
//            System.out.println("Invalid card details, payment failed");
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    public Booking bookASeat(int theatreId, int movieID, Seat seat) {
//        Booking booking;
//        Seat s = findASeat(theatreId,movieID,seat);
//        if (isSeatIdValid(s) && isSeatAvailable(s)) {
//            seat.setSeatInfo(SEAT_INFO.RESERVED.name());
//            booking = createABooking( movieID,movieID ,seat);
//            QueryDB.updateSeatStatus(theatreId,movieID,seat, SEAT_INFO.RESERVED.name());
//            QueryDB.findTheatreById(theatreId).saveBooking(booking);
//            return booking;
//        }
//        return null;
//    }
//
//    public boolean payForBooking(int theatreId,  String bookingID, Payment paymentDetails) {
//        // Variable to get the booking object based on the bookingID;
//        Booking booking = QueryDB.findABookingById(theatreId, bookingID);
//        if (booking != null) {
//            if (paymentDetails.getAmount() != booking.getPrice() || !this.validateCardDetails((paymentDetails))
//                    || isSeatBooked(booking.getSeat())) {
//                return false;
//            } else {
//                QueryDB.updateSeatStatus(theatreId,booking.getMovieID(),booking.getSeat(), SEAT_INFO.BOOKED.name());
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public boolean deleteBooking(int theatreId, String bookingId) {
//        Booking booking = QueryDB.findABookingById(theatreId, bookingId);
//        if (booking != null) {
//            Seat bookedSeat = booking.getSeat();
//            bookedSeat.setSeatInfo(SEAT_INFO.AVAILABLE.name());
//            QueryDB.resetSeatStatus(theatreId, bookingId);
//            QueryDB.deleteABooking(theatreId, bookingId);
//            return true;
//        }
//        return false;
//    }
//
//}
