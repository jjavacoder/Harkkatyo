package com.example.harkkatyo;

import android.content.Context;
import android.os.StrictMode;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLReaderExternal {
    XMLWriter writer = new XMLWriter();
    ArrayList<String> movies = new ArrayList<>();


    public ArrayList read(String URL, Context context) {
        try {
            //give permissions
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //create a new instance of DocumentBuilderFactory and??
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            //parse using the builder
            Document doc = builder.parse(URL);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getDocumentElement().getElementsByTagName("Event");


            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String name = element.getElementsByTagName("Title").item(0).getTextContent();
                    movies.add(name);

                }
            }
            writer.writeMovies(movies, context);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException parserConfigurationException) {
            parserConfigurationException.printStackTrace();
        }
    return movies;
    }
}
