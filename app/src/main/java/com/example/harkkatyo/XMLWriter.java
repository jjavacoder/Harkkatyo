package com.example.harkkatyo;

import android.content.Context;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLWriter {

    public void writeMovies(ArrayList<String> movies, Context context){
        OutputStreamWriter osw = null;
        int j = 0;



        try{
            osw = new OutputStreamWriter(context.openFileOutput("moviess.txt", Context.MODE_APPEND));
            System.out.println("KOKO: " + movies.size());
            for(int i = 0; i < movies.size(); i++) {
                System.out.println("ELOKUVA: " + movies.get(i));
                //int j = checkIfInList(context, movies.get(i));
                if(j == 0) {
                    System.out.println("KIRJOITTAA TIEDOSTOA");
                    String info = "<Movie>\n\t" + movies.get(i) + "\n</Movie>\n";
                    osw.write(info);

                    System.out.println("KANSION SIJAINTI: " + context.getFilesDir());
                }
            }
            osw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public int checkIfInList(Context context, String movieName){
        InputStream ins = null;
        try {
            ins = context.openFileInput("moviess.txt");
            System.out.println("KANSION SIJAINTI: " + context.getFilesDir());
            DocumentBuilder docB = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document xmlDoc = docB.parse(ins);
            String name = xmlDoc.getElementsByTagName("name").item(0).getTextContent();
            if(name.equals(movieName)){
                return 1;
            }
            else
                return 0;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


        return 1;
    }

    /*public void writeReviews(){
        OutputStreamWriter osw = null;
        String info ="<Reviews>\n" +
                "   <name>" + name + "</name>\n" +
                "   <date>" + date + "</date>\n" +
                "   <review>" + review + "</review>\n" +
                "   <stars>" + stars + "</stars>\n" +
                "</Reviews>\n";
        try{
            osw = new OutputStreamWriter(context.openFileOutput("reviews.txt", Context.MODE_APPEND));
            osw.write(info);
            osw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }*/
}
