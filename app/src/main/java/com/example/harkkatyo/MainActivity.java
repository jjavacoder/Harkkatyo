package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);


    }




    //Review movie Button, takes you to the review view
    //public void reviewLayoutButton(View view){
        //setContentView(R.layout.rewiev);
    //}


    public void mainpagelayoutButton(View view){
        setContentView(R.layout.activity_mainpage);
    }
    public void reviewslayoutButton(View view){
        setContentView(R.layout.activity_reviews);
    }
    public void archivelayoutButton(View view){
        setContentView(R.layout.activity_archive);
    }
}