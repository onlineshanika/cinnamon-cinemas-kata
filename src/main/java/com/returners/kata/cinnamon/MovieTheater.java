package com.returners.kata.cinnamon;

import java.util.ArrayList;
import java.util.List;

public class MovieTheater {

    private String name;
    private List<Theater> theaters;

    public MovieTheater(String name) {
        this.name = name;
        this.theaters = new ArrayList<Theater>();
    }

    public String getName() {
        return name;
    }

    public List<Theater> getTheaters() {
        return theaters;
    }

    public boolean addTheater(Theater theater) {
        if(validateTheaterName(theater.getName())){
            theaters.add(theater);
            return true ;
        }else {
            return false ;
        }
    }

    public void removeTheater(Theater theater) {
        theaters.remove(theater);
    }


    public List<Movie> viewMovies() {
        // Return a list of movies for all theaters
        List<Movie> movies = new ArrayList<Movie>();
        for (Theater theater : this.theaters) {
            movies.addAll(theater.getMovies());
        }
        return movies;
    }


    public ArrayList<Showtime> viewShowTime(Theater theater) {
        return new ArrayList<Showtime>();
    }

    public List<Movie> getMovies() {
        // Return a list of movies for all theaters
        List<Movie> movies = new ArrayList<Movie>();
        for (Theater theater : this.theaters) {
            movies.addAll(theater.getMovies());
        }
        return movies;
    }

    public List<Showtime> getShowtimes(Movie movie) {
        // Return a list of showtimes for a given movie
        List<Showtime> showtimes = new ArrayList<Showtime>();
        for (Theater theater : this.theaters) {
            showtimes.addAll(theater.getShowtimes(movie));
        }
        return showtimes;
    }


    private boolean validateTheaterName(String name){
        for (Theater theater : this.theaters) {
             if(name.equalsIgnoreCase(theater.getName())){
                 return false;
             }
        }
        return true;
    }
}


