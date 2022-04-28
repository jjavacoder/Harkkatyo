package com.example.harkkatyo.backend;


import com.example.harkkatyo.frontend.Reviews;

import java.util.Comparator;

public class Review  {
    private String movieName;
    private String date;
    private String text;
    private float stars;

    //constructor
    public Review(String movieName, String date, String text, float stars) {
        this.movieName = movieName;
        this.date = date;
        this.text = text;
        this.stars = stars;
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

    @Override
    public String toString() {
        return movieName +
                " " + date +
                "\n" + "'" + text + "'" +
                "   stars:" + stars
                ;
    }
    /*


    public static Comparator<Review>ReviewsNamesortComparator = new Comparator<Review>() {
        @Override
        public int compare(Review, Review) {
            return this.movieName.compareToIgnoreCase(review.movieName);
        }
    }
    public static Comparator<Reviews>ReviewsStarssortComparator = new Comparator<Reviews>() {
        @Override
        public int compare(Reviews reviews) {
            return this.stars < review.stars ? 1 : (this.stars > review.stars ? -1 : 0);

        }


    @Override
    public int compareTo(Review review) {
        return this.movieName.compareToIgnoreCase(review.movieName);

    }
    @Override
    public int compareTo() {
        return this.stars < review.stars ? 1 : (this.stars > review.stars ? -1 : 0);

}*/


}
