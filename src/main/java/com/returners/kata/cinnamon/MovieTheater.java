package com.returners.kata.cinnamon;

import java.util.ArrayList;
import java.util.List;

public class MovieTheater {

    private static MovieTheater movieTheater_instance = null;

    private String name;
    private List<Theatre> theaters;

    private MovieTheater() {
        this.name = "";
        this.theaters = new ArrayList<Theatre>();
    }

    public static MovieTheater getInstance() {
        if (movieTheater_instance == null)
            movieTheater_instance = new MovieTheater();

        return movieTheater_instance;
    }

    public String getName() {
        return name;
    }

    public List<Theatre> getTheaters() {
        return theaters;
    }

    public boolean addTheater(Theatre theater) {
        if (validateTheaterName(theater.getName())) {
            theaters.add(theater);
            return true;
        } else {
            return false;
        }
    }

    public void removeTheater(Theatre theater) {
        theaters.remove(theater);
    }


    public List<Movie> viewMovies() {
        // Return a list of movies for all theaters
        List<Movie> movies = new ArrayList<Movie>();
        for (Theatre theater : this.theaters) {
            movies.addAll(theater.getMovies());
        }
        return movies;
    }


    public ArrayList<Showtime> viewShowTime(Theatre theater) {
        return new ArrayList<Showtime>();
    }

    public List<Movie> getMovies() {
        // Return a list of movies for all theaters
        List<Movie> movies = new ArrayList<Movie>();
        for (Theatre theater : this.theaters) {
            movies.addAll(theater.getMovies());
        }
        return movies;
    }

    public List<Showtime> getShowtimes(Movie movie) {
        // Return a list of showtimes for a given movie
        List<Showtime> showtimes = new ArrayList<Showtime>();
        for (Theatre theater : this.theaters) {
            showtimes.addAll(theater.getShowtimes(movie));
        }
        return showtimes;
    }


    private boolean validateTheaterName(String name) {
        for (Theatre theater : this.theaters) {
            if (name.equalsIgnoreCase(theater.getName())) {
                return false;
            }
        }
        return true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMoviesForGivenTheatre(String theatre) {
        List<Movie> movies = new ArrayList<Movie>();
        for (Theatre theater : this.theaters) {
            if (theater.getName().equalsIgnoreCase(theatre)) {
                movies.addAll(theater.getMovies());
            }
        }
        return movies;
    }

    public List<Showtime> getShowtimes(String theatre, String movie) {
        // Return a list of showtimes for a given movie
        List<Showtime> showtimes = new ArrayList<Showtime>();
        for (Theatre theater : this.theaters) {
            if (theater.getName().equalsIgnoreCase(theatre)) {
                showtimes.addAll(theater.getShowtimes(new Movie(movie)));
            }
        }
        return showtimes;
    }

    public Theatre getTheaterByName(String theatre) {
        for (Theatre theater : this.theaters) {
            if (theater.getName().equalsIgnoreCase(theatre)) {
                return theater;
            }
        }
        return null;
    }
}


