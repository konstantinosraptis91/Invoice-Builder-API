/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.util;

import invoice.parser.entity.Customer;
import invoice.parser.entity.Invoice;
import invoice.parser.entity.Order;
import invoice.parser.entity.Supplier;
import invoice.parser.entity.Transporter;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author konstantinos
 */
public class XMLDocParser {
    // parse an xml file
    public static Invoice parse(File xmlFile) throws org.xml.sax.SAXException {
        Customer customer = new Customer();
        Order order = new Order();
        Supplier supplier = new Supplier();
        Transporter transporter = new Transporter();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();

            // Customer data parsing
            NodeList customerNList = document.getElementsByTagName("CUSTOMER");
            NodeList orderNList = document.getElementsByTagName("ORDER");
            NodeList supplierNList = document.getElementsByTagName("SUPPLIER");
            NodeList transporterNList = document.getElementsByTagName("TRANSPORTER");

            for (int i = 0; i < customerNList.getLength(); i++) {
                Node nNode = customerNList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    customer.setFullName(eElement.getElementsByTagName("FULL_NAME").item(0).getTextContent());
                    customer.setAddress(eElement.getElementsByTagName("ADDRESS").item(0).getTextContent());
                    customer.setPhoneNumber(eElement.getElementsByTagName("PHONE_NUMBER").item(0).getTextContent());
                    customer.setEmail(eElement.getElementsByTagName("E_MAIL").item(0).getTextContent());
                }
            }

            // Order data parsing
            for (int i = 0; i < orderNList.getLength(); i++) {
                Node nNode = orderNList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    
                    order.setProduct(eElement.getElementsByTagName("PRODUCT").item(0).getTextContent());
                    order.setQuantity(Integer.parseInt(eElement.getElementsByTagName("QUANTITY").item(0).getTextContent()));
                    order.setUnitCost(Float.parseFloat(eElement.getElementsByTagName("UNIT_COST").item(0).getTextContent()));
                    try {
                        order.setShippingDate(Constants.DATE_FORMAT.parse(eElement.getElementsByTagName("SHIPPING_DATE").item(0).getTextContent()));
                    } catch (ParseException ex) {
                        System.err.println("Date Parse Exception: " + ex.getMessage());
                    }
                }
            }

            // Supplier data parsing
            for (int i = 0; i < supplierNList.getLength(); i++) {
                Node nNode = supplierNList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    supplier.setFullName(eElement.getElementsByTagName("FULL_NAME").item(0).getTextContent());
                    supplier.setAddress(eElement.getElementsByTagName("ADDRESS").item(0).getTextContent());
                    supplier.setPhoneNumber(eElement.getElementsByTagName("PHONE_NUMBER").item(0).getTextContent());
                    supplier.setEmail(eElement.getElementsByTagName("E_MAIL").item(0).getTextContent());
                }
            }

            // Transporter data parsing
            for (int i = 0; i < transporterNList.getLength(); i++) {
                Node nNode = transporterNList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    transporter.setName(eElement.getElementsByTagName("NAME").item(0).getTextContent());
                    transporter.setAddress(eElement.getElementsByTagName("ADDRESS").item(0).getTextContent());
                    transporter.setPhoneNumber(eElement.getElementsByTagName("PHONE_NUMBER").item(0).getTextContent());
                    transporter.setEmail(eElement.getElementsByTagName("E_MAIL").item(0).getTextContent());
                }
            }

        } catch (ParserConfigurationException e) {
            System.err.println("ParserConfigurationException - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException - " + e.getMessage());
        }
        return new Invoice(customer, order, supplier, transporter);
    }
}
