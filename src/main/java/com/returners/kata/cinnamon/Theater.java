package com.returners.kata.cinnamon;

import com.returners.kata.cinnamon.util.Statuses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Theater {
    private String name;
    private Map<String, Seat> seats;
    private List<Showtime> showtimes;

    public Theater(String name) {
        this.name = name;
        this.seats = new HashMap<>();
        this.showtimes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Seat> getSeats() {
        return seats;
    }

    public void setSeats(Map<String, Seat> seats) {
        this.seats = seats;
    }

    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<Movie>();
        for (Showtime showtime : this.showtimes) {
            Movie movie = showtime.getMovie();
            if (!movies.contains(movie)) {
                movies.add(movie);
            }
        }
        return movies;
    }

    public List<Showtime> getShowtimes(Movie movie) {
        List<Showtime> showtimes = new ArrayList<Showtime>();
        for (Showtime showtime : this.showtimes) {
            if (showtime.getMovie().equals(movie)) {
                showtimes.add(showtime);
            }
        }
        return showtimes;
    }

    public void addShowTimes(Showtime showtime) {
        this.showtimes.add(showtime);
    }


    public void createSeats(int noOfRows, char noOfCols) {
        this.seats = new HashMap<>();
        char c;
        for (int i = 1; i <= noOfRows; i++) {
            for (c = 'A'; c <= noOfCols; ++c)
                this.seats.put(c + "#" + i, new Seat(c, i, Statuses.AVAILABLE));
        }
    }

    public boolean isSeatAvailable(String seatNumber) {
        if (seatNumber != null) {
            Seat seat = seats.get(seatNumber);
            if (seat != null) {
                return seat.getStatus() == Statuses.AVAILABLE;
            }
        }
        return false;
    }

    public Seat getSeat(String seatNumber) {
        if (seatNumber != null) {
            return seats.get(seatNumber);

        }
        return null;
    }
}






