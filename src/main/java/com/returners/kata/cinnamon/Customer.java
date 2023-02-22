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

    public Booking bookSeats(String movie, String showTimeStr, String theater, String[] seats) {
        List<Seat>  seatList = new ArrayList<>();
        List<Showtime> showtimes = MovieTheater.getInstance().getShowtimes(new Movie(movie));
        Showtime showtime = null;
        for (Showtime show : showtimes) {
            if (show.getStart_time().equalsIgnoreCase(showTimeStr)) {
                showtime = show;
                break;
            }
        }
        Theatre theatre =  MovieTheater.getInstance().getTheaterByName(theater) ;
        for (String requestedSeat : seats) {
            Seat seat = theatre.getSeat(requestedSeat,showTimeStr);
            seat.setStatus(Statuses.RESERVED);
            seatList.add(seat);
        }

        if(reservedSeatsMap == null){
            reservedSeatsMap = new HashMap<>();
        }
        reservedSeatsMap.put(movie+"#"+showTimeStr+"#"+theater,seatList);
        return new Booking(showtime.getMovie(),showtime,seatList,new Customer());

    }

}
