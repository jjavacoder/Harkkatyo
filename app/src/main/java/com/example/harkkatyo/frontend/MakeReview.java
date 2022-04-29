package com.example.harkkatyo.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harkkatyo.R;
import com.example.harkkatyo.backend.ReviewHandler;

import java.util.Calendar;

public class MakeReview extends AppCompatActivity {
    private DatePickerDialog datePicker;
    private Button dateButton;
    private Button submitbutton;
    EditText reviewText;
    String name;
    String date;
    String text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makereview);

        final RatingBar ratingbar = findViewById(R.id.ratingBar);
        submitbutton = findViewById(R.id.submitButton);
        reviewText = (EditText) findViewById(R.id.writeReview);


        //Getting the name of the movie from Archive
        TextView Display = findViewById(R.id.movieName);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String moviename = extras.getString("key");
            Display.setText(String.valueOf(moviename));
            name = moviename;
        }

        //click event on submit button
        submitbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                float stars = ratingbar.getRating();
                text = reviewText.getText().toString();
                // If all information is given the information gets saved
                if (stars != 0 && text != null && date != null) {

                    ReviewHandler reviewHandler = new ReviewHandler();
                    reviewHandler.addReview(name, date, text, stars);

                    changelayout();

                    name = null;
                    date = null;
                    text = null;
                    stars = 0;
                } else if(stars == 0) {
                    Toast.makeText(MakeReview.this, "Rate the movie",Toast.LENGTH_SHORT).show();
                }else if(reviewText.getText() == null) {
                    Toast.makeText(MakeReview.this, "Write review",Toast.LENGTH_SHORT).show();
                }else if(date == null) {
                    Toast.makeText(MakeReview.this, "Choose date",Toast.LENGTH_SHORT).show();
                }
            }
        });
        initDatePicker();
        dateButton = findViewById(R.id.selectDate);

        Button btrb4 = findViewById(R.id.buttonrb4);
        btrb4.setOnClickListener(new View.OnClickListener() {
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

    //Date picker button
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month +1;
                date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePicker = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month)+ " " + day + " " + year;
    }
    private String getMonthFormat(int month){
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";
        //default return
        return "JAN";

    }

    public void openDatePicker(View V){
        datePicker.show();
    }

    public void changelayout(){
        Intent intent = new Intent(MakeReview.this, Reviews.class);
        startActivity(intent);
    }


    /*public void archivelayoutButton(View view){
        setContentView(R.layout.activity_archive);
    }*/

}