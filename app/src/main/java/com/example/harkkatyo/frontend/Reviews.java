package com.example.harkkatyo.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.harkkatyo.R;
import com.example.harkkatyo.backend.Review;
import com.example.harkkatyo.backend.ReviewHandler;

import java.util.ArrayList;

public class Reviews extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.sortnames));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        ReviewHandler reviewHandler = new ReviewHandler();
        ArrayList<Review> reviews = reviewHandler.getReviews();
        System.out.println(reviews.get(0).getMovieName());
        System.out.println(reviews.get(0).getDate());
        System.out.println(reviews.get(0).getText());
        System.out.println(reviews.get(0).getStars());
        System.out.println("moi");

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