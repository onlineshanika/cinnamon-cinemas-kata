package com.returners.kata.cinnamon;

public class Showtime {

    private String start_time;
    private String end_time;
//    private Theatre theater;
    private Movie movie;


    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

//    public Theatre getTheater() {
//        return theater;
//    }
//
//    public void setTheater(Theatre theater) {
//        this.theater = theater;
//    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
