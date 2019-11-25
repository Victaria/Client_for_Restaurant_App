package Parsers;

import Entities.Dishes;
import Entities.Products;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

public class DOMParser {

    private  String path = "D:\\Disk_D\\VTPart2\\DataBase\\";
    private  String filePath = "D:\\Disk_D\\VTPart2\\src\\sample\\XML\\";
    private static Logger log = LogManager.getLogger();

    private ObservableList<Products> productsList = FXCollections.observableArrayList();
    private ObservableList<Dishes> dishesList = FXCollections.observableArrayList();

    public ObservableList<Products> loadProductXMLFile() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            factory.setValidating(false);

            DocumentBuilder builder = null;
            try {
                builder = factory.newDocumentBuilder();

                FileInputStream fis = new FileInputStream(path + "ProductsXML.xml");
                InputSource is = new InputSource(fis);

                Document doc = builder.parse(is);
                NodeList productNodes = doc.getElementsByTagName("product");

                for (int i = 0; i < productNodes.getLength(); i++) {
                    Node productNode = productNodes.item(i);

                    if (productNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element productElement = (Element) productNode;

                        String id = productElement.getElementsByTagName("id").item(0).getTextContent();
                        String name = productElement.getElementsByTagName("name").item(0).getTextContent();
                        String price = productElement.getElementsByTagName("price").item(0).getTextContent();
                        String amount = productElement.getElementsByTagName("amount").item(0).getTextContent();

                        Products product = new Products(Integer.valueOf(id), name, Double.valueOf(price), Integer.valueOf(amount));

                        productsList.add(product);
                    }

                }
                log.log(Level.INFO, "Product File loaded");
                return productsList;
            } catch (ParserConfigurationException | FileNotFoundException e) {
                log.log(Level.ERROR, "Parse/FileNotFound Exception", e);
                return null;
            } catch (SAXException e) {
                log.log(Level.ERROR, "SAX Exception", e);
                return null;
            } catch (IOException e) {
                log.log(Level.ERROR, "IO Exception", e);
                return null;
            }
        } catch (Exception e) {
            log.log(Level.ERROR, "Exception", e);
            return null;
        }

    }

    public  ObservableList<Dishes> loadDishesXMLFile() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            factory.setValidating(false);

            DocumentBuilder builder = null;
            try {
                builder = factory.newDocumentBuilder();

                FileInputStream fis = new FileInputStream(path + "DishesXML.xml");
                InputSource is = new InputSource(fis);

                Document doc = builder.parse(is);
                NodeList dishNodes = doc.getElementsByTagName("dish");

                for (int i = 0; i < dishNodes.getLength(); i++) {
                    Node dishNode = dishNodes.item(i);

                    if (dishNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element dishElement = (Element) dishNode;

                        String id = dishElement.getElementsByTagName("id").item(0).getTextContent();
                        String name = dishElement.getElementsByTagName("name").item(0).getTextContent();
                        String price = dishElement.getElementsByTagName("price").item(0).getTextContent();
                        String weight = dishElement.getElementsByTagName("weight").item(0).getTextContent();
                        String sum = dishElement.getElementsByTagName("sum").item(0).getTextContent();

                        Dishes dish = new Dishes(Integer.valueOf(id), name, Double.valueOf(price), Double.valueOf(weight), Double.valueOf(sum));

                        dishesList.add(dish);
                    }

                }
                log.log(Level.INFO, "Dishes File loaded");
                return dishesList;
            } catch (ParserConfigurationException | FileNotFoundException e) {
                log.log(Level.ERROR, "Parse/FileNotFound Exception", e);
                // e.printStackTrace();
                return null;
            } catch (SAXException e) {
                log.log(Level.ERROR, "SAX Exception", e);
                return null;
            } catch (IOException e) {
                log.log(Level.ERROR, "IO Exception", e);
                return null;
            }
        } catch (Exception e) {
            log.log(Level.ERROR, "Exception", e);
            return null;
        }
    }



}
