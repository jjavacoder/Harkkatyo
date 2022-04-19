package com.example.harkkatyo;

import java.util.ArrayList;

public class MovieList {
    ArrayList<Movie> movies = new ArrayList<>();
    public void add(Movie movie){
        movies.add(movie);

    }
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void print() {
        for (int i = 0; i < movies.size(); i++) {
            String name = movies.get(i).getName();
            System.out.println(name);
        }
    }
}
