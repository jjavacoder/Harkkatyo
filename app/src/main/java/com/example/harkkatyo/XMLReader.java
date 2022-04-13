package com.example.harkkatyo;

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

public class XMLReader {

    public ArrayList read(String XMLAddress) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(XMLAddress);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getDocumentElement().getElementsByTagName("TheatreArea");

            for (int i = 0; i < nList.getLength(); i++) {

                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String Name = element.getElementsByTagName("Name").item(0).getTextContent();
                    String id = element.getElementsByTagName("ID").item(0).getTextContent();
                    int idInt = Integer.parseInt(id);
                    Theater th = new Theater(Name, idInt);
                    theaterInfo.add(th);
                    theaters.add(Name);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException parserConfigurationException) {
            parserConfigurationException.printStackTrace();
        }
        return theaters;
    }
}
