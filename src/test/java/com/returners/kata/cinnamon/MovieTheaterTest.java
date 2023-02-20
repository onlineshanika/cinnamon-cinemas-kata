package com.returners.kata.cinnamon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTheaterTest {

    @Test
    public void createMovieTheater(){

        MovieTheater movieTheater = new MovieTheater("MovieTheater 1");
        Theater theater = new Theater();
        theater.setName(" Theater 3D -1 ");
        assertEquals(Boolean.TRUE ,movieTheater.addTheater(theater));

    }

    @Test
    public void createMovieTheaterDuplicateCheck(){

        MovieTheater movieTheater = new MovieTheater("MovieTheater 1");
        Theater theater1 = new Theater();
        theater1.setName(" Theater 3D -1 ");
        assertEquals(Boolean.TRUE ,movieTheater.addTheater(theater1));

        Theater theater2 = new Theater();
        theater2.setName(" Theater 3D -1 ");
        assertEquals(Boolean.FALSE ,movieTheater.addTheater(theater2));

    }
}
