package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class Mainpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
    }
    public void reviewslayoutButton(View view){
        setContentView(R.layout.activity_reviews);
    }
    public void archivelayoutButton(View view){
        setContentView(R.layout.activity_archive);
    }
}