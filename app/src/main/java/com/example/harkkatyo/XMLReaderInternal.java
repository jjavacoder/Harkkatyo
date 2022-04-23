package com.example.harkkatyo;

import android.content.Context;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLReaderInternal {

    public void read(Context context){
        try {
            System.out.println("KANSION SIJAINTI: " + context.getFilesDir());
            InputStream ins = context.openFileInput("movie.txt");
            DocumentBuilder docB = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document xmlDoc = docB.parse(ins);

            String name = xmlDoc.getElementsByTagName("name").item(0).getTextContent();
            //String date = xmlDoc.getElementsByTagName("date").item(0).getTextContent();
            //String review = xmlDoc.getElementsByTagName("review").item(0).getTextContent();
            //String stars = xmlDoc.getElementsByTagName("stars").item(0).getTextContent();

            System.out.println("NIMI: " + name);
            //System.out.println("PVM: " + date);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }

}
