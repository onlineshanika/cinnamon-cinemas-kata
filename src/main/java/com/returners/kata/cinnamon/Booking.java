package com.returners.kata.cinnamon;

import java.util.List;

public class Booking {

    private Movie movie;
    private Showtime showtime;
    private List<Seat> seats;
    private Customer customerID;


    public Booking(Movie movie, Showtime showtime, List<Seat> seats, Customer customerID) {
        this.movie = movie;
        this.showtime = showtime;
        this.seats = seats;
        this.customerID = customerID;
    }

    public Movie getMovie() {
        return movie;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Customer getCustomerID() {
        return customerID;
    }
}
