package com.example.harkkatyo;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

public class XMLWriter {
    private Context context;

    public void write(String name, Date date, String review, float stars ){
        OutputStreamWriter osw = null;
        //File path = getApplicationContext().getFilesDir();
        String info ="<Reviews>\n" +
                "   <name>" + name + "</name>\n" +
                "   <date>" + date + "</date>\n" +
                "   <review>" + review + "</review>\n" +
                "   <stars>" + stars + "</stars>\n" +
                "</Reviews>";
        try{
            osw = new OutputStreamWriter(context.openFileOutput("reviews.txt", Context.MODE_PRIVATE));
            osw.write(info);
            osw.close();
            /*FileOutputStream writer = new FileOutputStream(new File(path, fileName), true);
            writer.write(content.getBytes());
            writer.close();*/
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
