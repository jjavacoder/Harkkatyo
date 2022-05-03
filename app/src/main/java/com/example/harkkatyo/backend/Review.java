package com.example.harkkatyo.backend;


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

    //vertaa movieNameja toisiinsa, myöhemmin collections.sort järjestää ne a->z
    public static Comparator<Review>ReviewsNamesortComparator = new Comparator<Review>() {
        @Override
        public int compare(Review t1, Review t2) {
            return t1.movieName.compareToIgnoreCase(t2.movieName);
        }
    };

    //vertaa tähtien määrää eli lukua float toisiinsa, jonka jälkeen collections.sort
    //järjestää ne riippuen palautuneesta arvosta
    public static Comparator<Review>StarssortComparator = new Comparator<Review>() {
        @Override
        public int compare(Review t1, Review t2) {
            return t1.stars < t2.stars ? 1 : (t1.stars > t2.stars ? -1 : 0);
        }};



    public String getMovieName() {
        return movieName;
    }

    public String getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public float getStars() {

        return stars;
    }

    public String getDirector() {
        return director;
    }
    //Muotoilee textviewin
   @Override
    public String toString() {
        return date +
                "       " + movieName +
                "\n" + "STARS: " + stars+"        Director: " + director+
                "\n''" + text + "''"
                ;

    }



}
