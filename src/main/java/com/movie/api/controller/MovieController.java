package com.movie.api.controller;

import com.movie.api.model.Booking;
import com.movie.api.response.Response;
import com.movie.api.service.CinemaService;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/////TO CLARIFY A CONFUSION, A THEATRE WOULD EQUIVALENT TO A MOVIE HOUSE,
// WHILE A CINEMA IS AN INDIVIDUAL ROOM. THIS WILL BE REFACTORED
//Get All Theatres
//Get Theatre by id
//Get movies by theatres
//Get All Cinemas
//Get Cinema by id
//Get cinema by movieId and theatreId
//Get all seats per cinema
//Get All movies
//Get movie by id
//Get All Reservation
//Get reservation by Id
//Get All screenings
//Get screening by id
//get seat_reservation by screening

//book a seat
//delete a reservation
@RestController
public class MovieController implements ErrorController {
    private static final String ERROR_PATH = "/error";
    private String notFound = "The URL provided does not identify any resource";
    private CinemaService cinemaService = new CinemaService();

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(ERROR_PATH)
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    public Response handleError() {
        return new Response.Builder("").responseStatus(HttpStatus.NOT_FOUND).message(notFound).build();
    }

    //Theatre CONTROLLER
    @RequestMapping(value = "theatres", method = RequestMethod.GET )
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    private Response getAllTheatres() {
        return new Response.Builder(cinemaService.findAllTheatre()).responseStatus(HttpStatus.OK).message("SUCCESS").build();
    }

    @RequestMapping(value = "theatres/{theatreId}", method = RequestMethod.GET )
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    private Response getTheatreById(@PathVariable("theatreId") String id) {
        return new Response.Builder(cinemaService.findTheatreById(id)).responseStatus(HttpStatus.OK).message("SUCCESS").build();
    }

    @RequestMapping(value = "theatres/{theatreId}/movies", method = RequestMethod.GET )
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    private Response getMoviesByTheatre(@PathVariable("theatreId") String id) {
        return new Response.Builder(cinemaService.getMoviesByTheatre(id)).responseStatus(HttpStatus.OK).message("SUCCESS").build();
    }


    //CINEMA CONTROLLER
    @RequestMapping(value = "cinemas", method = RequestMethod.GET )
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    private Response getAllCinemas() {
        return new Response.Builder(cinemaService.findAllCinemas()).responseStatus(HttpStatus.OK).message("SUCCESS").build();
    }

    @RequestMapping(value = "cinemas/{cinemaId}", method = RequestMethod.GET )
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    private Response getCinemaById(@PathVariable("cinemaId") String id) {
        return new Response.Builder(cinemaService.findCinemaById(id)).responseStatus(HttpStatus.OK).message("SUCCESS").build();
    }
    @RequestMapping(value = "theatres/{theatreId}/movies/{movieId}/cinema", method = RequestMethod.GET )
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    private Response getCinemaByMovieAndTheatre(@PathVariable("theatreId") int idTheatre, @PathVariable("movieId") int idMovie) {
        return new Response.Builder(cinemaService.getCinemaByMovieAndTheatre(idTheatre,idMovie)).responseStatus(HttpStatus.OK).message("SUCCESS").build();
    }

    @RequestMapping(value = "cinemas/{cinemaId}/seats", method = RequestMethod.GET )
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    private Response getSeatByCinema(@PathVariable("cinemaId") String id) {
        return new Response.Builder(cinemaService.findSeatByCinema(id)).responseStatus(HttpStatus.OK).message("SUCCESS").build();
    }

    @RequestMapping(value = "cinemas/{cinemaId}/occupiedSeats", method = RequestMethod.GET )
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    private Response getOccupiedSeatByCinema(@PathVariable("cinemaId") int id) {
        return new Response.Builder(cinemaService.getOccupiedSeats(id)).responseStatus(HttpStatus.OK).message("SUCCESS").build();
    }

    //MOVIE CONTROLLER
    @RequestMapping(value = "movies", method = RequestMethod.GET )
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    private Response getAllMovies() {
        return new Response.Builder(cinemaService.findAllMovies()).responseStatus(HttpStatus.OK).message("SUCCESS").build();
    }

    @RequestMapping(value = "movies/{movieId}", method = RequestMethod.GET )
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    private Response getMoviesById(@PathVariable("movieId") String id) {
        return new Response.Builder(cinemaService.findMovieById(id)).responseStatus(HttpStatus.OK).message("SUCCESS").build();
    }

    //RESERVATION CONTROLLER
    @RequestMapping(value = "reservations", method = RequestMethod.GET )
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    private Response getAllReservations() {
        return new Response.Builder(cinemaService.findAllReservations()).responseStatus(HttpStatus.OK).message("SUCCESS").build();
    }

    @RequestMapping(value = "reservations/{reservationId}", method = RequestMethod.GET )
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    private Response getReservationById(@PathVariable("reservationId") String id) {
        return new Response.Builder(cinemaService.findReservationById(id)).responseStatus(HttpStatus.OK).message("SUCCESS").build();
    }

    @RequestMapping(value = "reservations/{reservationId}/delete-booking", method = RequestMethod.GET )
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    private Response deleteBooking(@PathVariable("reservationId") String id) {
        return new Response.Builder(cinemaService.deleteBooking(Integer.valueOf(id))).responseStatus(HttpStatus.OK).message("SUCCESS").build();
    }

    //SCREENING CONTROLLER FUNCTIONALITY
    @RequestMapping(value = "screenings", method = RequestMethod.GET )
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    private Response getAllScreenings() {
        return new Response.Builder(cinemaService.findAllScreenings()).responseStatus(HttpStatus.OK).message("SUCCESS").build();
    }

    @RequestMapping(value = "screenings/{screeningId}", method = RequestMethod.GET )
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    private Response getScreeningById(@PathVariable("screeningId") String id) {
        return new Response.Builder(cinemaService.findScreeningById(id)).responseStatus(HttpStatus.OK).message("SUCCESS").build();
    }

    @RequestMapping(value = "screenings/{screeningId}/seat_reservations", method = RequestMethod.GET )
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    private Response getReservationsByScreening(@PathVariable("screeningId") String id) {
        return new Response.Builder(cinemaService.findReservationByScreening(id)).responseStatus(HttpStatus.OK).message("SUCCESS").build();
    }


    @RequestMapping(value = "theatres/{theatreId}/movies/{movieId}/book-a-seat", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    public Response bookATicket(@RequestBody Booking reservation) {
        int seatAvailableId = cinemaService.bookSeat(reservation.getSeatId(),reservation.getScreeningId(),reservation.getName(),reservation.getSeatId());
        if (seatAvailableId>=1) {
            return new Response.Builder(seatAvailableId).responseStatus(HttpStatus.CREATED).message("SUCCESS").build();
        }
        return new Response.Builder("").responseStatus(HttpStatus.CONFLICT).message("SEAT UNAVAILABLE").build();
    }


//    @RequestMapping(value = "theatres/{theatreId}/movies", method = RequestMethod.GET )
//    @ResponseBody
//    @CrossOrigin(origins = "http://localhost:4200")
//    private Response getAllMovies(@PathVariable("theatreId") int id) {
//        return new Response.Builder(theatreService.findAllMovies(id)).responseStatus(HttpStatus.OK).message("Success").build();
//    }

//    @RequestMapping(value = "theatres/{theatreId}/movies/{movieId}", method = RequestMethod.GET )
//    @ResponseBody
//    @CrossOrigin(origins = "http://localhost:4200")
//    private Response getMovieById(@PathVariable("theatreId") int theatreId, @PathVariable("movieId") int movieId) {
//        return new Response.Builder(theatreService.findMovieById(theatreId, movieId)).responseStatus(HttpStatus.OK).message("SUCCESS").build();
//    }
//
//    @RequestMapping(value = "theatres/{theatreId}/movies/{movieId}/seats", method = RequestMethod.GET )
//    @ResponseBody
//    @CrossOrigin(origins = "http://localhost:4200")
//    private Response getSeats(@PathVariable("theatreId") int theatreId, @PathVariable("movieId") int movieId) {
//        return new Response.Builder(theatreService.findSeats(theatreId, movieId)).responseStatus(HttpStatus.OK).message("SUCCESS").build();
//    }
//
//    @RequestMapping(value = "theatres/{theatreId}/movies/{movieId}/book-a-seat", method = RequestMethod.POST)
//    @ResponseBody
//    @CrossOrigin(origins = "http://localhost:4200")
//    public Response bookATicket(@PathVariable("theatreId") int theatreId, @PathVariable("movieId") int movieId, @RequestBody Seat seat) {
//        Booking booking = bookingService.bookASeat(theatreId, movieId, seat);
//        if (booking != null) {
//            return new Response.Builder(booking).responseStatus(HttpStatus.CREATED).message("SUCCESS").build();
//        }
//        return new Response.Builder("").responseStatus(HttpStatus.CONFLICT).message("SEAT UNAVAILABLE").build();
//    }
//
//    @RequestMapping(value = "theatres/{theatreId}/pay-booking", method = RequestMethod.POST)
//    @ResponseBody
//    @CrossOrigin(origins = "http://localhost:4200")
//    public Response payForBooking(@PathVariable("theatreId") int theatreId, @RequestBody Payment paymentDetails) {
//        boolean result = bookingService.payForBooking(theatreId, paymentDetails.getBookingID(), paymentDetails);
//        if (result) {
//            return new Response.Builder(paymentDetails).responseStatus(HttpStatus.CREATED).message("Payment was successful.  Booking Id : " + paymentDetails.getBookingID()).build();
//        }
//        return new Response.Builder(paymentDetails).responseStatus(HttpStatus.NOT_ACCEPTABLE).message("Payment not Accepted").build();
//    }
//
//    @RequestMapping(value = "theatres/{theatreId}/bookings", method = RequestMethod.GET )
//    @ResponseBody
//    @CrossOrigin(origins = "http://localhost:4200")
//    private Response getAllBooking(@PathVariable("theatreId") int id) {
//        return new Response.Builder(theatreService.findAllBookings(id)).responseStatus(HttpStatus.OK).message("Success").build();
//    }
//
//    @RequestMapping(value = "theatres/{theatreId}/cancel-booking/{bookingId}", method = RequestMethod.GET)
//    @ResponseBody
//    @CrossOrigin(origins = "http://localhost:4200")
//    public Response deleteBooking(@PathVariable("theatreId") int theatreId, @PathVariable("bookingId") String bookingId) {
//        boolean result = bookingService.deleteBooking(theatreId, bookingId);
//        if (result) {
//            return  new Response.Builder("").message("booking id " +bookingId+ " was removed successfully.").responseStatus(HttpStatus.OK).build();
//        }
//        return new Response.Builder("").responseStatus(HttpStatus.NOT_FOUND).message(notFound).build();
//    }

}
