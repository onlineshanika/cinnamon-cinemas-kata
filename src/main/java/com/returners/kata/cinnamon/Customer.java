package com.returners.kata.cinnamon;

import com.returners.kata.cinnamon.util.Statuses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer extends User {

    private static Map<String,List<Seat>> reservedSeatsMap ;

    public void viewMovies() {

    }

    public void viewShowtimes() {

    }

    public void cancelBooking() {

    }

    public Booking bookSeats(String movie, String showTime, String theater, String[] seats) {
        List<Seat>  seatList = new ArrayList<>();
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
            seatList.add(seat);
        }

        if(reservedSeatsMap == null){
            reservedSeatsMap = new HashMap<>();
        }
        reservedSeatsMap.put(movie+"#"+showTime+"#"+theater,seatList);
        return new Booking(showtimeOBJ.getMovie(),showtimeOBJ,seatList,new Customer());

    }

    public void bookSeats(String movie, String[] seats) {

    }

    public void purchaseTickets(Booking booking) {

        //calculate total

    }
}
