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

public class ReviewHandler {
    private static final String ELEMENT_REVIEWS = "Reviews";
    private static final String ELEMENT_REVIEW = "Review";
    private static final String ELEMENT_NAME = "Name";
    private static final String ELEMENT_DATE = "Date";
    private static final String ELEMENT_TEXT = "Text";
    private static final String ELEMENT_STARS = "Stars";
    private static final String ELEMENT_DIRECTOR = "Director";

    private static File path = App.getContext().getFilesDir();
    private static String filePath = path + "/reviews.xml";

    private ReviewHandler(){

    }

    //writes review to XML file
    public static void addReview(String movieName, String date, String text, float stars, String director) {
        Review review = new Review(movieName, date, text, stars, director);
        Document doc = null;
        Element rootElement = null;

        //checking if the file exists or not
        if (checkIfReviewXMLExists()) {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
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
            //get root element
            NodeList nList = doc.getElementsByTagName(ELEMENT_REVIEWS);
            Node node = nList.item(0);
            rootElement = (Element) node;

        } else {
            //create file
            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                // set root element
                doc = docBuilder.newDocument();
                rootElement = doc.createElement(ELEMENT_REVIEWS);
                doc.appendChild(rootElement);

            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        }
        //set review element
        Element reviewElement = doc.createElement(ELEMENT_REVIEW);
        rootElement.appendChild(reviewElement);

        //set name element
        Element nameElement = doc.createElement(ELEMENT_NAME);
        nameElement.setTextContent(review.getMovieName());
        reviewElement.appendChild(nameElement);

        //set date element
        Element dateElement = doc.createElement(ELEMENT_DATE);
        dateElement.setTextContent(review.getDate());
        reviewElement.appendChild(dateElement);

        //set text element
        Element textElement = doc.createElement(ELEMENT_TEXT);
        textElement.setTextContent(review.getText());
        reviewElement.appendChild(textElement);

        //set stars element
        Element starsElement = doc.createElement(ELEMENT_STARS);
        starsElement.setTextContent(String.valueOf(review.getStars()));
        reviewElement.appendChild(starsElement);

        //set director element
        Element directorElement = doc.createElement(ELEMENT_DIRECTOR);
        reviewElement.appendChild(directorElement);
        directorElement.setTextContent(String.valueOf(review.getDirector()));

        writeXMLFile(filePath, doc);
    }

    //check if file exists
    public static boolean checkIfReviewXMLExists(){
        File file = new File(filePath);
        if (file.exists()){
            return true;
        }
        else{
            return false;
        }
    }

    //reads XMl file and returns arraylist that includes reviews
    public static ArrayList<Review> getReviews() {
        ArrayList<Review> reviews = new ArrayList<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //read reviews from XML
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(filePath));
            NodeList list = doc.getElementsByTagName(ELEMENT_REVIEW);
            //go through the nodelist
            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String name = element.getElementsByTagName(ELEMENT_NAME).item(0).getTextContent();
                    String date = element.getElementsByTagName(ELEMENT_DATE).item(0).getTextContent();
                    String text = element.getElementsByTagName(ELEMENT_TEXT).item(0).getTextContent();
                    String starsString = element.getElementsByTagName(ELEMENT_STARS).item(0).getTextContent();
                    String director = element.getElementsByTagName(ELEMENT_DIRECTOR).item(0).getTextContent();
                    float stars = Float.parseFloat(starsString);
                    //create an instance of review
                    Review review = new Review(name, date, text, stars, director);
                    //add review instance to reviews arraylist
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

    //writes document with elements to a file
    private static void writeXMLFile(String filePath, Document doc){
        FileOutputStream output = null;
        Context context = App.getContext();
        //write doc to file
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
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}