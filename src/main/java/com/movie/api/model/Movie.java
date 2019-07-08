package com.movie.api.model;


import java.util.Date;

public class Movie {
    private Integer movieId;
    private String title;
    private Date showing;

    public Movie() {}

    public Movie(Integer movieId, String title, Date showing) {
        this.movieId = movieId;
        this.title = title;
        this.showing = showing;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setitle(String title) {
        this.title = title;
    }

    public String getShowing() {
        return String.valueOf(showing);
    }

    public void setShowing(Date showing) {
        this.showing = showing;
    }
}
