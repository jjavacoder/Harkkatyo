package com.example.harkkatyo.backend;


import java.util.Date;

public class Review {
    String movieName;
    String date;
    String text;
    float stars;



    public Review(String movieName, String date, String text, float stars){

        this.movieName = movieName;
        this.date = date;
        this.text = text;
        this.stars = stars;
    }

    public String getMovieName(){
        return movieName;
    }

    public String getDate(){
        return date;
    }
    public String getText(){
        return text;
    }
    public float getStars(){
        return stars;
    }




}
