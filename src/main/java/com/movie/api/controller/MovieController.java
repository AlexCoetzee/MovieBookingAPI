package com.movie.api.controller;

import com.movie.api.response.Response;
import com.movie.api.service.BookingService;
import com.movie.api.service.CinemaService;
import com.movie.api.service.TheatreService;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController implements ErrorController {
    private static final String ERROR_PATH = "/error";
    private String notFound = "The URL provided does not identify any resource";
    private CinemaService cinemaService = new CinemaService();
    private TheatreService theatreService = new TheatreService();
    private BookingService bookingService = new BookingService();

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(ERROR_PATH)
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:8080")
    public Response handleError() {
        return new Response.Builder("").responseStatus(HttpStatus.NOT_FOUND).message(notFound).build();
    }


    @RequestMapping(value = "cinemas", method = RequestMethod.GET )
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:8080")
    private Response getAllCinemas() {
        return new Response.Builder(cinemaService.findAllCinemas()).responseStatus(HttpStatus.OK).message("SUCCESS").build();
    }

    @RequestMapping(value = "cinemas/{cinemaId}", method = RequestMethod.GET )
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:8080")
    private Response getTheatreById(@PathVariable("cinemaId") String id) {
        return new Response.Builder(cinemaService.findCinemaById(id)).responseStatus(HttpStatus.OK).message("SUCCESS").build();
    }


    //SCREENING CONTROLLER FUNCTIONALITY
    @RequestMapping(value = "screenings", method = RequestMethod.GET )
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:8080")
    private Response getAllScreenings() {
        return new Response.Builder(cinemaService.findAllScreenings()).responseStatus(HttpStatus.OK).message("SUCCESS").build();
    }

    @RequestMapping(value = "screenings/{screeningId}", method = RequestMethod.GET )
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:8080")
    private Response getScreeningById(@PathVariable("screeningId") String id) {
        return new Response.Builder(cinemaService.findScreeningById(id)).responseStatus(HttpStatus.OK).message("SUCCESS").build();
    }

    @RequestMapping(value = "screenings/{screeningId}/reservations", method = RequestMethod.GET )
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:8080")
    private Response getReservationsByScreening(@PathVariable("screeningId") String id) {
        return new Response.Builder(cinemaService.findReservationByScreening(id)).responseStatus(HttpStatus.OK).message("SUCCESS").build();
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
