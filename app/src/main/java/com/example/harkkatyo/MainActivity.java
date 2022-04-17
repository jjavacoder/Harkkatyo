package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);



    }


    //Review movie Button, takes you to the review view
    //public void reviewLayoutButton(View view){
        //setContentView(R.layout.rewiev);
    //}


    public void mainpagelayoutButton(View view){
        setContentView(R.layout.mainpage);
    }
    public void reviewslayoutButton(View view){
        setContentView(R.layout.reviews);
    }
}