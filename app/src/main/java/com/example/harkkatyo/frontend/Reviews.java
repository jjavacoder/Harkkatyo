package com.example.harkkatyo.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.text.method.ScrollingMovementMethod;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harkkatyo.R;
import com.example.harkkatyo.backend.Review;
import com.example.harkkatyo.backend.ReviewHandler;

import java.util.ArrayList;
import java.util.Collections;

public class Reviews extends AppCompatActivity {

    TextView myList;
    ArrayList<Review> reviews = new ArrayList<>();



    

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        myList = findViewById(R.id.mytxt);
        myList.setMovementMethod(new ScrollingMovementMethod());


        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.sortnames));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);


        //check if the review XML file exists
        if (ReviewHandler.checkIfReviewXMLExists()) {
            reviews = ReviewHandler.getReviews();
        } else {
            Toast.makeText(Reviews.this, "The list is empty. Make reviews.", Toast.LENGTH_SHORT).show();
        }


        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int y, long l) {
                if (y == 1) {
                    Collections.sort(reviews, Review.ReviewsNamesortComparator);//järjestää tulokset a->z
                    //clears textview before adding new
                    myList.setText("");


                } else {
                    //järjestää tulokset tähtien mukaan
                    Collections.sort(reviews, Review.StarssortComparator);
                    //clears textview before adding new
                    myList.setText("");


                }
                showList();//näyttää textviewiin lisätyt arvostelut
                //muotoiltuna ja järjestettynä
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    // mikäli on osa listaa, lisää listaan ja kutsuu toString muotoilua
    private void showList() {
        for (Review tmp : reviews) {
            myList.append((tmp.toString() + "\n\n"));

        }


        //Back painikkeen toiminto eli muuttaa näkymää takaisin Mainactivityyn
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
    }
}