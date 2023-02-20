package com.returners.kata.cinnamon;

import java.util.ArrayList;
import java.util.List;

public class Theater {
    private String name;
    private List<Seat> seats;
    private List<Showtime> showtimes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
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
}





