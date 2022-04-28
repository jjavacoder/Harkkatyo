package com.example.harkkatyo.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.harkkatyo.R;
import com.example.harkkatyo.backend.Review;
import com.example.harkkatyo.backend.ReviewHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reviews extends AppCompatActivity {

    ListView myList;
    List<Review> mList = new ArrayList<Review>();
    ArrayList<Review> reviews = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        myList = findViewById(R.id.mytxt);

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.sortnames));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);



        ReviewHandler reviewHandler = new ReviewHandler();
        int returnNumber = reviewHandler.checkIfExists();
        if (returnNumber == 1) {
            reviews = reviewHandler.getReviews();
            System.out.println("Movie name: " + reviews.get(0).getMovieName());
            System.out.println("Date: " + reviews.get(0).getDate());
            System.out.println(reviews.get(0).getText());
            System.out.println(reviews.get(0).getStars());
        }


            //lista läpi for loopilla ja siitä olioy

        for(int i = 0; i<reviews.size();i++) {
            //int number = reviews.get(i).compare(reviews.get(i));
            Review number = reviews.get(i);
            //reviews.get(i).getDate();
            //reviews.get(i).getText();
            //reviews.get(i).getStars();

        }
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int y, long l) {
                if(y==0){
                    Collections.sort(reviews,Review.ReviewsNamesortComparator);

                }else{
                    Collections.sort(reviews,Review.StarssortComparator);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });







        Button bt2 = findViewById(R.id.buttonrb);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadActivity();
            }


        });

    }
    public void loadActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }/*
    public void mainpagelayoutButton(View view){
        setContentView(R.layout.activity_mainpage);
    }*/
}