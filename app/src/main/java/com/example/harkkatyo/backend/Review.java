package com.example.harkkatyo.backend;


import com.example.harkkatyo.frontend.Reviews;

import java.util.Comparator;

public class Review  {
    private String movieName;
    private String date;
    private String text;
    private float stars;
    private String director;

    //constructor
    public Review(String movieName, String date, String text, float stars, String director) {
        this.movieName = movieName;
        this.date = date;
        this.text = text;
        this.stars = stars;
        this.director = director;
    }

    public static Comparator<Review>ReviewsNamesortComparator = new Comparator<Review>() {
        @Override
        public int compare(Review t1, Review t2) {
            return t1.movieName.compareToIgnoreCase(t2.movieName);
        }
    };
    public static Comparator<Review>StarssortComparator = new Comparator<Review>() {
        @Override
        public int compare(Review t1, Review t2) {
            return t1.stars < t2.stars ? 1 : (t1.stars > t2.stars ? -1 : 0);
        }};


    public String getMovieName() {
        return movieName;
    }
   // public void setMovieName(String movieName){this.movieName = movieName;}

    public String getDate() {
        return date;
    }
    //public void setDate(String date){this.date = date;}

    public String getText() {
        return text;
    }
    //public void setText(String text){this.text = text;}

    public float getStars() {

        return stars;
    }
    //public void setStars(float stars){this.stars = stars;}

    public String getDirector() {
        return director;
    }

    @Override
    public String toString() {
        return date +
                "       " + movieName +
                "\n" + "STARS: " + stars+
                "           ''" + text + "''"
                ;

    }



}
