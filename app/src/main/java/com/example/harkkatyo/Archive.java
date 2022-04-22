package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Archive extends AppCompatActivity {
    String[] movies2 = {"oubfkb", "gg", "jghv"};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);

        //Archive list
        listView = findViewById(R.id.movieList);
        //connect data
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Archive.this, android.R.layout.simple_expandable_list_item_1, movies2);
        //create listview obj
        listView.setAdapter(adapter);

        //Item clicked event
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //show clicked message
                Toast.makeText(Archive.this, "You clicked" +movies2[position],Toast.LENGTH_SHORT).show();
            }
        });
    }
    /*public void mainpagelayoutButton(View view){
        setContentView(R.layout.activity_mainpage);
    }*/
}