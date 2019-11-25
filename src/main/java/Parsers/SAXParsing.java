package Parsers;

import Entities.OrderDish;
import Entities.Order;
import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.time.LocalDate;

public class SAXParsing {
    private static String path = "D:\\Disk_D\\VTPart2\\DataBase\\";

    private static Logger log = LogManager.getLogger();
    private static OrderDish od = null;
    private static Order order = null;

    private static ObservableList<OrderDish> orderDishesList = FXCollections.observableArrayList();
    private static ObservableList<Order> ordersList = FXCollections.observableArrayList();

    public static ObservableList<OrderDish> orderDishParse(){
        try {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();

        DefaultHandler handler = new DefaultHandler() {
            boolean odId = false;
            boolean odAmount = false;
            boolean odDishName = false;
            boolean odOrderId = false;

            public void startElement(String uri, String localName,String qName,
                                     Attributes attributes) throws SAXException {

                if (qName.equalsIgnoreCase("ID")) {
                    odId = true;
                    od = new OrderDish(0, 0 ,"", 0);
                }

                if (qName.equalsIgnoreCase("AMOUNT")) {
                    odAmount = true;
                }

                if (qName.equalsIgnoreCase("DISHNAME")) {
                    odDishName = true;
                }

                if (qName.equalsIgnoreCase("ORDERID")) {
                    odOrderId = true;
                }
        }
            public void endElement(String uri, String localName,
                                   String qName) throws SAXException {
                if (qName.equalsIgnoreCase("ORDERDISH")) {
                    orderDishesList.add(od);
                }
            }

            public void characters(char ch[], int start, int length) throws SAXException {

                if (odId) {
                    od.setId(Integer.parseInt(new String(ch, start, length)));
                    odId = false;
                }

                if (odAmount) {
                    od.setAmount(Integer.parseInt(new String(ch, start, length)));
                    odAmount = false;
                }

                if (odDishName) {
                    od.setDishName(new String(ch, start, length));
                    odDishName = false;
                }

                if (odOrderId) {
                    od.setOrderId(Integer.parseInt(new String(ch, start, length)));
                    //orderDishesList.add(od);
                    odOrderId = false;
                }
            }
        };

        saxParser.parse(path + "OrderDishXML.xml" , handler);

    } catch (Exception e) {
            log.log(Level.ERROR, "Exception by parsing DishFile", e);
    }
        return orderDishesList;
    }

    public static ObservableList<Order> orderParse(){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                boolean orderId = false;
                boolean table = false;
                boolean sum = false;
                boolean date = false;
                boolean staffName = false;

                public void startElement(String uri, String localName,String qName,
                                         Attributes attributes) throws SAXException {

                    if (qName.equalsIgnoreCase("ID")) {
                        orderId = true;
                        order = new Order(0, 0 ,0, null, "");
                    }

                    if (qName.equalsIgnoreCase("TABLE")) {
                        table = true;
                    }

                    if (qName.equalsIgnoreCase("SUM")) {
                        sum = true;
                    }

                    if (qName.equalsIgnoreCase("DATE")) {
                        date = true;
                    }

                    if (qName.equalsIgnoreCase("STAFFNAME")) {
                        staffName = true;
                    }
                }
                public void endElement(String uri, String localName,
                                       String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("ORDER")) {
                        ordersList.add(order);
                    }
                }

                public void characters(char ch[], int start, int length) throws SAXException {

                    if (orderId) {
                        order.setId(Integer.parseInt(new String(ch, start, length)));
                        orderId = false;
                    }

                    if (table) {
                        order.setTable(Integer.parseInt(new String(ch, start, length)));
                        table = false;
                    }

                    if (sum) {
                        order.setSum(Double.parseDouble(new String(ch, start, length)));
                        sum = false;
                    }

                    if (date) {
                        order.setDate(LocalDate.parse(new String(ch, start, length)));
                        //orderDishesList.add(od);
                        date = false;
                    }

                    if (staffName){
                        order.setStaffName(new String(ch, start, length));
                        staffName = false;
                    }
                }
            };

            saxParser.parse(path + "OrdersXML.xml" , handler);

        } catch (Exception e) {
            log.log(Level.ERROR, "Exception by parsing Order File", e);
        }
        return ordersList;
    }

}
