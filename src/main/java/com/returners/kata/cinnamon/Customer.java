package com.returners.kata.cinnamon;

import com.returners.kata.cinnamon.util.Statuses;

import java.util.List;

public class Customer extends User {

    public void viewMovies() {

    }

    public void viewShowtimes() {

    }

    public void cancelBooking() {

    }

    public void bookSeats(String movie, String showTime, String theater, String[] seats) {

        List<Showtime> showtimes = MovieTheater.getInstance().getShowtimes(new Movie(movie));
        Showtime showtimeOBJ = null;
        for (Showtime show : showtimes) {
            if (show.getTheater().getName().equalsIgnoreCase(theater) && show.getStart_time().equalsIgnoreCase(showTime)) {
                showtimeOBJ = show;
                break;
            }
        }
        for (String requestedSeat : seats) {
            Seat seat = showtimeOBJ.getTheater().getSeat(requestedSeat);
            seat.setStatus(Statuses.RESERVED);
        }

    }

    public void bookSeats(String movie, String[] seats) {

    }

    public void purchaseTickets() {

    }
}
