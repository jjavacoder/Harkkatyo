package com.example.harkkatyo;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XMLWriter {

    public void write(ArrayList<String> movies){
        File path = App.getContext().getFilesDir();
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("movies");
            doc.appendChild(rootElement);

            Element name = doc.createElement("name");
            //rootElement.appendChild(name);

            name.setTextContent("007 James Bond");
            rootElement.appendChild(name);

            FileOutputStream output = new FileOutputStream(path);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //pretty print XML
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(output);
            transformer.transform(source, result);

        } catch (ParserConfigurationException parserConfigurationException) {
            parserConfigurationException.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    public void writeMovies(ArrayList<String> movies){
        File path = App.getContext().getFilesDir();
        Context context;
        int j = 0;
        try{

            System.out.println("KOKO: " + movies.size());
            FileOutputStream writer = new FileOutputStream(new File(path, "movies.xml"), true);
            writer.write("<Movies>\n".getBytes());
            for(int i = 0; i < movies.size(); i++) {
                System.out.println("ELOKUVA: " + movies.get(i));
                //int j = checkIfInList(context, movies.get(i));
                if(j == 0) {
                    System.out.println("KIRJOITTAA TIEDOSTOA");
                    String info = "\t<Movie>\n\t\t<name>" + movies.get(i) + "</name>\n\t</Movie>\n";
                    info = info.replaceAll("&","&amp;");
                    writer.write(info.getBytes());

                    System.out.println("Kansion sijainti: " + path);

                }
            }
            writer.write("</Movies>".getBytes());
            writer.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public int checkIfInList(Context context, String movieName){
        InputStream ins = null;
        String remove = "</Movies>";
        int k = 0;
        try {
            ins = context.openFileInput("movies.xml");
            System.out.println("KANSION SIJAINTI: " + context.getFilesDir());
            DocumentBuilder docB = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document xmlDoc = docB.parse(ins);
            String name = xmlDoc.getElementsByTagName("name").item(0).getTextContent();
            if(name.equals(movieName)){
                return 1;
            }
            else
                if(k == 0){

            }
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
