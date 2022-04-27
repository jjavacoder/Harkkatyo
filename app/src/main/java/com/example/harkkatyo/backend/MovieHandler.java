package com.example.harkkatyo.backend;

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
import java.util.ArrayList;

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

public class MovieHandler {
    private static final String ELEMENT_MOVIES = "Movies";
    private static final String ELEMENT_MOVIE = "Movie";
    private static final String ELEMENT_NAME = "Name";
    File path = App.getContext().getFilesDir();
    String filePath = path + "/movies.xml";

    public void addMovie(ArrayList<String> movies) {
        File file = new File(filePath);

        //test if the file already exists or not
        if (file.exists()) {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            NodeList list = null;
            Document doc = null;
            try {
                InputStream is = new FileInputStream(filePath);
                DocumentBuilder db = docFactory.newDocumentBuilder();
                //parse xml that already exists
                doc = db.parse(is);
                //create a nodelist of movie elements
                list = doc.getDocumentElement().getElementsByTagName(ELEMENT_MOVIE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

            //go through movies in the arraylist
            for (int i = 0; i < movies.size(); i++) {
                String movieName = movies.get(i);
                boolean exists = false;
                //go through movies in the XML file
                for (int j = 0; j < list.getLength(); j++) {
                    Node node = list.item(j);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        //check if the movie from the arraylist is already in xml file
                        Element nameElement = (Element) node;
                        String nameFromXML = nameElement.getElementsByTagName(ELEMENT_NAME).item(0).getTextContent();
                        if (movieName.equalsIgnoreCase(nameFromXML)) {
                            exists = true;
                            break;
                        }
                    }
                }
                //if doesn't exist yet -> set new movie
                if (!exists) {

                    //get root element
                    NodeList nList = doc.getElementsByTagName(ELEMENT_MOVIES);
                    Node node = nList.item(0);
                    Element moviesElement = (Element) node;

                    //set movie element
                    Element movieElement = doc.createElement(ELEMENT_MOVIE);
                    moviesElement.appendChild(movieElement);

                    //set name element
                    Element nameElement = doc.createElement(ELEMENT_NAME);
                    movieElement.appendChild(nameElement);
                    nameElement.setTextContent(movieName);
                }
            }
            writeXMLFile(filePath, doc);
        } else {
            //create file
            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                //set root element
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement(ELEMENT_MOVIES);
                doc.appendChild(rootElement);

                //make elements of all movies
                for (int i = 0; i < movies.size(); i++) {

                    //set movie element
                    Element movie = doc.createElement(ELEMENT_MOVIE);
                    rootElement.appendChild(movie);

                    //set name element
                    Element name = doc.createElement(ELEMENT_NAME);
                    name.setTextContent(movies.get(i));
                    movie.appendChild(name);
                }
                writeXMLFile(filePath, doc);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<String> getArrayList(){
        //read Finnkino XML file
        XMLReaderExternal reader = new XMLReaderExternal();
        ArrayList<String> moviesToXML = reader.read();
        //write movies to XML file if needed
        addMovie(moviesToXML);
        //read all movies from XML file
        ArrayList<String> movies = getMovies();
        return movies;
    }

    public void writeXMLFile(String filePath, Document doc){
        FileOutputStream output = null;
        Context context = App.getContext();
        //write doc to file
        try {
            output = context.openFileOutput("movies.xml",context.MODE_PRIVATE);
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

    public ArrayList <String> getMovies() {
        ArrayList<String> movies = new ArrayList<>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //read movies from xml and add to movies arraylist
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(filePath));
            NodeList list = doc.getElementsByTagName(ELEMENT_MOVIE);
            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String name = element.getElementsByTagName(ELEMENT_NAME).item(0).getTextContent();
                    movies.add(name);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return movies;
    }
}