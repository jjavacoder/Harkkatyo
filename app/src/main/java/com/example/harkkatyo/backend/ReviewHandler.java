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

public class ReviewHandler {
    private static final String ELEMENT_NAME = "name";
    private static final String ELEMENT_DATE = "date";
    private static final String ELEMENT_TEXT = "text";
    private static final String ELEMENT_STARS = "stars";
    File path = App.getContext().getFilesDir();
    String filePath = path + "/reviews.xml";

    public void addReview(String movieName, String date, String text, float stars) {
        File file = new File(filePath);
        Review review = new Review(movieName, date, text, stars);
        System.out.println("ReviewHandler, addreview");

        if (file.exists()) {
            System.out.println("Tiedosto on olemassa");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            Document doc = null;
            try {
                InputStream is = new FileInputStream(filePath);
                DocumentBuilder db = docFactory.newDocumentBuilder();
                //parse xml that already exists
                doc = db.parse(is);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
            //lisätäänkö? miten toimii, kun rootElement tehty aiemmin
            //Element reviewElement = doc.createElement("review");
            //rootElement.appendChild(reviewElement);


            //setting name
            Element nameElement = doc.createElement(ELEMENT_NAME);
            nameElement.setTextContent(review.getMovieName());

            //setting date
            Element dateElement = doc.createElement(ELEMENT_DATE);
            dateElement.setTextContent(review.getDate());

            //setting text
            Element textElement = doc.createElement(ELEMENT_TEXT);
            textElement.setTextContent(review.getText());

            //setting stars
            Element starsElement = doc.createElement(ELEMENT_STARS);
            starsElement.setTextContent(String.valueOf(review.getStars()));

            writeXMLFile(filePath, doc);
        } else {
            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                //root elements
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("Reviews");
                doc.appendChild(rootElement);


                Element reviewElement = doc.createElement("review");
                rootElement.appendChild(reviewElement);

                //setting name
                Element nameElement = doc.createElement(ELEMENT_NAME);
                nameElement.setTextContent(review.getMovieName());
                reviewElement.appendChild(nameElement);

                //setting date
                Element dateElement = doc.createElement(ELEMENT_DATE);
                dateElement.setTextContent(review.getDate());
                reviewElement.appendChild(dateElement);

                //setting text
                Element textElement = doc.createElement(ELEMENT_TEXT);
                textElement.setTextContent(review.getText());
                reviewElement.appendChild(textElement);

                //setting stars
                Element starsElement = doc.createElement(ELEMENT_STARS);
                starsElement.setTextContent(String.valueOf(review.getStars()));
                reviewElement.appendChild(starsElement);

                writeXMLFile(filePath, doc);

            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }

        }
    }
    public ArrayList<Review> getReviews() {
        ArrayList<Review> reviews = new ArrayList<>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(filePath));
            NodeList list = doc.getElementsByTagName("review");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String date = element.getElementsByTagName("date").item(0).getTextContent();
                    String text = element.getElementsByTagName("text").item(0).getTextContent();
                    String starsString = element.getElementsByTagName("stars").item(0).getTextContent();
                    float stars = Float.parseFloat(starsString);
                    System.out.println("Review: " + name + date);
                    Review review = new Review(name, date, text, stars);
                    reviews.add(review);

                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public void writeXMLFile(String filePath, Document doc){
        FileOutputStream output = null;
        Context context = App.getContext();
        try {
            output = context.openFileOutput("reviews.xml",context.MODE_PRIVATE);
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
}
