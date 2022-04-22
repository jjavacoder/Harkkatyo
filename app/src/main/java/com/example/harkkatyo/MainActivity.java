package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt2 = findViewById(R.id.buttonrb);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadActivity();
            }


        });
        Button bt3 = findViewById(R.id.button3);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadArchive();
            }
        });





    }
    public void loadActivity(){
        Intent intent = new Intent(this, Reviews.class);
        startActivity(intent);


    } public void loadArchive(){
        Intent intent2 = new Intent(this, Archive.class);
        startActivity(intent2);
    }
}




    //Review movie Button, takes you to the review view
    //public void reviewLayoutButton(View view){
        //setContentView(R.layout.rewiev);
    //}


