package com.example.harkkatyo.frontend;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.harkkatyo.R;
import com.example.harkkatyo.backend.App;
import com.example.harkkatyo.backend.MovieHandler;
import com.example.harkkatyo.backend.XMLReaderExternal;

import java.io.File;
import java.util.ArrayList;

public class Archive extends AppCompatActivity {

    ListView listView;
    Context context;
    EditText searchmovie;
    File path = App.getContext().getFilesDir();
    String filePath = path + "/movies.xml";
    MovieHandler movieHandler = new MovieHandler();
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);

        searchmovie = (EditText) findViewById(R.id.search);

        ArrayList<String> movies = movieHandler.getArrayList();

        //Archive list
        listView = findViewById(R.id.movieList);

        //connect data
        adapter = new ArrayAdapter<>(Archive.this, R.layout.changecolor, movies);
        //create listview obj
        listView.setAdapter(adapter);

        //Search function
        searchmovie.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                (Archive.this).adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Item clicked event
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String moviename = movies.get(movies.indexOf(adapter.getItem(position)));
                //show clicked movie
                Toast.makeText(Archive.this, "You clicked " + moviename,Toast.LENGTH_SHORT).show();
                //                //Takes you to review movie
                //String moviename = movies.get(position);
                Intent intent = new Intent(Archive.this, Review.class);
                intent.putExtra("key", moviename);
                startActivity(intent);
                finish();
            }
        });

        searchmovie.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    searchmovie.getText().clear();
                }
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
