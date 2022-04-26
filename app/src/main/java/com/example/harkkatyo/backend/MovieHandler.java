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
    private static final String ELEMENT_NAME = "name";
    File path = App.getContext().getFilesDir();
    String filePath = path + "/movies.xml";

    public void addMovie(ArrayList<String> movies) {
        System.out.println("OSOITE: " + path);
        File file = new File(filePath);

        if (file.exists()) {
            System.out.println("Tiedosto on olemassa");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            NodeList list = null;
            Document doc = null;
            try {
                InputStream is = new FileInputStream(filePath);
                DocumentBuilder db = docFactory.newDocumentBuilder();
                doc = db.parse(is);
                list = doc.getElementsByTagName(ELEMENT_NAME);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < movies.size(); i++) {
                String movieName = movies.get(i);
                boolean exists = false;
                for (int j = 0; i < list.getLength(); i++) {
                    Node node = list.item(j);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        System.out.println("EKA IF");
                        if (movieName.equalsIgnoreCase(node.getNodeName())) {
                            System.out.println("TOKA IF");
                            exists = true;
                            break;
                        }
                    }
                }

                if (!exists) {
                    System.out.println("ELSE");
                    Element name = doc.createElement(ELEMENT_NAME);
                    //rootElement.appendChild(name);

                    name.setTextContent(movieName);
                    //rootElement.appendChild(name);
                }
            }
            writeXMLFile(filePath, doc);
        } else {
            System.out.println("Tiedosto ei ole olemassa");
            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                //root elements
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("movies");
                doc.appendChild(rootElement);

                //making elements of all movies
                for (int i = 0; i < movies.size(); i++) {
                    Element movie = doc.createElement("movie");
                    rootElement.appendChild(movie);

                    Element name = doc.createElement("name");

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
        XMLReaderExternal reader = new XMLReaderExternal();
        ArrayList<String> moviesToXML = reader.read();
        addMovie(moviesToXML);
        ArrayList<String> movies = getMovies();
        return movies;
    }



    public void writeXMLFile(String filePath, Document doc){
        FileOutputStream output = null;
        Context context = App.getContext();
        try {
            //output = new FileOutputStream(filePath);
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

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(filePath));
            NodeList list = doc.getElementsByTagName("movie");
            System.out.println("NODELIST LENGTH: " + list.getLength());

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    movies.add(name);
                    System.out.println(name);
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

