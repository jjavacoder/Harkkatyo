package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class Archive extends AppCompatActivity {
    String URL = "https://www.finnkino.fi/xml/Events/";
    String[] movies2 = {"oubfkb", "gg", "jghv"};
    ListView listView;
    Context context;
    File path = App.getContext().getFilesDir();
    String filePath = path + "/movies.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);


        //reading Finnkinos XML file
        XMLReaderExternal readerE = new XMLReaderExternal();
        readerE.read(URL);

        //reading internal XML file
        XMLReaderInternal readerI = new XMLReaderInternal();
        ArrayList<String> movies = readerI.read(filePath);



        //Archive list
        listView = findViewById(R.id.movieList);

        //connect data
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Archive.this, R.layout.changecolor, movies);
        //create listview obj
        listView.setAdapter(adapter);

        //Item clicked event
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //show clicked movie
                Toast.makeText(Archive.this, "You clicked " + movies.get(position),Toast.LENGTH_SHORT).show();
                //Takes you to review movie
                String moviename = movies.get(position);
                Intent intent = new Intent(Archive.this, Review.class);
                intent.putExtra("key", moviename);
                startActivity(intent);
                finish();
            }
        });

        Button bt2 = findViewById(R.id.buttonab);
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
    /*public void mainpagelayoutButton(View view){
        setContentView(R.layout.activity_mainpage);
    }*/
