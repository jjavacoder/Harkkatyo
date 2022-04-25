package com.example.harkkatyo;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLReaderInternal {

    public ArrayList <String> read(String fileName){
        Context context;
        ArrayList<String> movies = new ArrayList<>();
        try {
            context = App.getContext();
            InputStream ins = context.openFileInput(fileName);
            DocumentBuilder docB = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document xmlDoc = docB.parse(ins);
            NodeList nList = xmlDoc.getDocumentElement().getElementsByTagName("Movie");
            System.out.println("LISTAN KOKO: " + nList.getLength());

            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    movies.add(name);
                }

                //String date = xmlDoc.getElementsByTagName("date").item(0).getTextContent();
                //String review = xmlDoc.getElementsByTagName("review").item(0).getTextContent();
                //String stars = xmlDoc.getElementsByTagName("stars").item(0).getTextContent();


                //System.out.println("PVM: " + date);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
