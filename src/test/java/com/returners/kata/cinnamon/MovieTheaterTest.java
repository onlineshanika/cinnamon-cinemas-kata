package com.returners.kata.cinnamon;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTheaterTest {

    static MovieTheater movieTheater  ;
    static Theatre theater1  ;
    @BeforeAll
    static void setup() {
        movieTheater =  MovieTheater.getInstance();
        movieTheater.setName("MovieTheater 1");
        theater1 = new Theatre("Theater 3D");
    }


    @Test
    public void createMovieTheater() {
        assertEquals(Boolean.TRUE, movieTheater.addTheater(theater1));

    }

    @Test
    public void createMovieTheaterDuplicateCheck() {

         assertEquals(Boolean.FALSE, movieTheater.addTheater(theater1));

        Theatre theater2 = new Theatre(" Theater 3D -2 ");
         assertEquals(Boolean.TRUE, movieTheater.addTheater(theater2));

    }


    @Test
    public void createShowTime() {

        if (movieTheater.addTheater(theater1)) {
            Movie movie1 = new Movie("Ant-Man and the Wasp", " Adventure/Action ", 3,
                    125, "Ant-Man and the Wasp find themselves exploring the Quantum Realm," +
                    " interacting with strange new creatures and embarking on an adventure " +
                    "that pushes them beyond the limits of what they thought was possible.");

            Showtime showtime = new Showtime();
            showtime.setMovie(movie1);
//            showtime.setTheater(theater1);
            showtime.setStart_time("14:00");
            showtime.setEnd_time("16:00");
            theater1.addShowTimes(showtime);

            Showtime showtime2 = new Showtime();
            showtime2.setMovie(movie1);
//            showtime2.setTheater(theater1);
            showtime2.setStart_time("16:00");
            showtime2.setEnd_time("18:00");
            theater1.addShowTimes(showtime2);


            Movie movie2 = new Movie("Ant-Man and the Wasp");

            assertEquals(2,theater1.getShowtimes(movie2).size());
        }

    }


    @Test
    public void createSeats() {
        theater1.createSeats(5,'C',"10:00");
        assertEquals(15,theater1.getSeats().size());
    }


    @Test
    public void checkSeatAvailability() {

        theater1.createSeats(5,'C',"10:00");
        assertEquals(Boolean.TRUE,theater1.isSeatAvailable("B#4","10:00"));
        assertEquals(Boolean.FALSE,theater1.isSeatAvailable("T#4","10:00"));
    }


}
