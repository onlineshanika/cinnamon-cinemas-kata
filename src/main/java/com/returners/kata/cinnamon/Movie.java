package com.returners.kata.cinnamon;

import java.util.Objects;

public class Movie {

    private String title;
    private String genre;
    private double rating;
    private float duration;
    private String description;

    public Movie(String title) {
        this.title = title;
    }

    public Movie(String title, String genre, double rating, float duration, String description) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.duration = duration;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return title.equalsIgnoreCase(movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}

