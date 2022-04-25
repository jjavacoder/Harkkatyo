package com.example.harkkatyo;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
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

    public void writeMovies(ArrayList<String> movies){
        File path = App.getContext().getFilesDir();
        String filePath = path + "/movies.xml";
        System.out.println("OSOITE: " + path);
        int k = 0;
        File file = new File(filePath);

        if (file.exists()) {
            System.out.println("Tiedosto on olemassa");

            for (int i = 0; i < movies.size(); i++) {
                int l = 0;
                //check if the movie is already in the xml file
                //if movie is in the list the method returns 1 and if it's not it returns 0
                int number = checkIfInList(filePath, movies.get(i));
                if (number == 0) {
                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    try {
                        InputStream is = new FileInputStream(filePath);
                        DocumentBuilder db = docFactory.newDocumentBuilder();
                        Document doc = db.parse(is);
                        NodeList list = doc.getElementsByTagName("name");
                        for (int j = 0; i < list.getLength(); i++) {
                            Node node = list.item(j);
                            if (node.getNodeType() == Node.ELEMENT_NODE) {
                                System.out.println("EKA IF");
                                if (movies.get(i).equalsIgnoreCase(node.getNodeName())) {
                                    System.out.println("TOKA IF");
                                    break;
                                } else {
                                    System.out.println("ELSE");
                                    Element name = doc.createElement("name");
                                    //rootElement.appendChild(name);

                                    name.setTextContent(movies.get(i));
                                    //rootElement.appendChild(name);
                                }

                            }
                        }

                        } catch(FileNotFoundException e){
                            e.printStackTrace();
                        } catch(ParserConfigurationException e){
                            e.printStackTrace();
                        } catch(IOException e){
                            e.printStackTrace();
                        } catch(SAXException e){
                            e.printStackTrace();
                        }
                    }

                }
            }
        else{
            System.out.println("Tiedosto ei ole olemassa");
                try {
                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                    //root elements
                    Document doc = docBuilder.newDocument();
                    Element rootElement = doc.createElement("movies");
                    doc.appendChild(rootElement);


                    for(int i = 0;i< movies.size();i++) {
                        Element movie = doc.createElement("movie");
                        rootElement.appendChild(movie);


                        Element name = doc.createElement("name");

                        name.setTextContent(movies.get(i));
                        movie.appendChild(name);
                    }
                    write(filePath, doc);

                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }

            }
        }


    public void write(String filePath, Document doc){
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(filePath);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = null;
            transformer = transformerFactory.newTransformer();

            //pretty print XML
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(output);
            transformer.transform(source, result);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }


    }
    public int checkIfInList(String filePath, String movieName){
        int k = 0;
        try {
            InputStream is = new FileInputStream(filePath);
            DocumentBuilder docB = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document xmlDoc = docB.parse(is);
            /*String name = xmlDoc.getElementsByTagName("name").item(0).getTextContent();
            if(name.equals(movieName)){
                return 1;
            }
            else
                return 0;*/
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
