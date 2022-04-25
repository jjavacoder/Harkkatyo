package com.example.harkkatyo.backend;


import java.util.Date;

public class Review {
    String name;
    Date date;
    String text;
    float stars;



    public Review(String name, Date date, String text, float stars){

        this.name = name;
        this.date = date;
        this.text = text;
        this.stars = stars;
    }

    public String getName(){
        return name;
    }



}
