package invoice.parser.util;

import invoice.parser.entity.Customer;
import invoice.parser.entity.Order;
import invoice.parser.entity.Supplier;
import invoice.parser.entity.Transporter;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.DOMException;

/**
 *
 * @author etern
 */

public class XMLDocBuilder {

    // create xml doc
    public static void create(String fileName, Customer customer,
            Order order, Supplier supplier, Transporter transporter) {
        Document doc;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.newDocument();
            /////////////////////////////////////////////////////////////////////
            //CONFIGURE XML FILE

            // root element - INVOICE
            Element rootElement = doc.createElement("INVOICE");
            doc.appendChild(rootElement);

            //******************************************************************
            // CUSTOMER element and its sub-elements
            Element customerElement = doc.createElement("CUSTOMER");
            Element customerIdElement = doc.createElement("ID");
            Element customerFullNameElement = doc.createElement("FULL_NAME");
            Element customerAddressElement = doc.createElement("ADDRESS");
            Element customerPhoneNumberElemenet = doc.createElement("PHONE_NUMBER");
            Element customerEmailElement = doc.createElement("E_MAIL");
            // Set elements text
            customerIdElement.setTextContent(String.valueOf(customer.getId()));
            customerFullNameElement.setTextContent(customer.getFullName());
            customerAddressElement.setTextContent(customer.getAddress());
            customerPhoneNumberElemenet.setTextContent(customer.getPhoneNumber());
            customerEmailElement.setTextContent(customer.getEmail());
            // Append to CUSTOMER element
            customerElement.appendChild(customerIdElement);
            customerElement.appendChild(customerFullNameElement);
            customerElement.appendChild(customerAddressElement);
            customerElement.appendChild(customerPhoneNumberElemenet);
            customerElement.appendChild(customerEmailElement);
            //******************************************************************
            // ORDER element and its sub-elements
            Element orderElement = doc.createElement("ORDER");
            Element orderIdElement = doc.createElement("ID");
            Element orderProductElement = doc.createElement("PRODUCT");
            Element orderTotalCostElement = doc.createElement("TOTAL_COST");
            Element orderShippingDateElement = doc.createElement("SHIPPING_DATE");
            // Set elements text
            orderIdElement.setTextContent(String.valueOf(order.getId()));
            orderProductElement.setTextContent(order.getProduct());
            orderTotalCostElement.setTextContent(String.valueOf(order.getTotalCost()));
            orderShippingDateElement.setTextContent(order.getShippingDate().toString());
            // Append to ORDER element
            orderElement.appendChild(orderIdElement);
            orderElement.appendChild(orderProductElement);
            orderElement.appendChild(orderTotalCostElement);
            orderElement.appendChild(orderShippingDateElement);
            //*******************************************************************
            // SUPPLIER element and its sub-elements
            Element supplierElement = doc.createElement("SUPPLIER");
            Element supplierIdElement = doc.createElement("ID");
            Element supplierFullNameElement = doc.createElement("FULL_NAME");
            Element supplierAddressElement = doc.createElement("ADDRESS");
            Element supplierPhoneNumberElemenet = doc.createElement("PHONE_NUMBER");
            Element supplierEmailElement = doc.createElement("E_MAIL");
            // Set elements text
            supplierIdElement.setTextContent(String.valueOf(supplier.getId()));
            supplierFullNameElement.setTextContent(supplier.getFullName());
            supplierAddressElement.setTextContent(supplier.getAddress());
            supplierPhoneNumberElemenet.setTextContent(supplier.getPhoneNumber());
            supplierEmailElement.setTextContent(supplier.getEmail());
            // Append to SUPPLIER element
            supplierElement.appendChild(supplierIdElement);
            supplierElement.appendChild(supplierFullNameElement);
            supplierElement.appendChild(supplierAddressElement);
            supplierElement.appendChild(supplierPhoneNumberElemenet);
            supplierElement.appendChild(supplierEmailElement);
            //*********************************************************************
            // TRANSPORTER element and its sub-elements
            Element transporterElement = doc.createElement("TRANSPORTER");
            Element transporterIdElement = doc.createElement("ID");
            Element transporterNameElement = doc.createElement("NAME");
            Element transporterAddressElement = doc.createElement("ADDRESS");
            Element transporterPhoneNumberElemenet = doc.createElement("PHONE_NUMBER");
            Element transporterEmailElement = doc.createElement("E_MAIL");
            // Set elements text
            transporterIdElement.setTextContent(String.valueOf(transporter.getId()));
            transporterNameElement.setTextContent(transporter.getName());
            transporterAddressElement.setTextContent(transporter.getAddress());
            transporterPhoneNumberElemenet.setTextContent(transporter.getPhoneNumber());
            transporterEmailElement.setTextContent(transporter.getEmail());
            // Append to TRANSPORTER element
            transporterElement.appendChild(transporterIdElement);
            transporterElement.appendChild(transporterNameElement);
            transporterElement.appendChild(transporterAddressElement);
            transporterElement.appendChild(transporterPhoneNumberElemenet);
            transporterElement.appendChild(transporterEmailElement);
            //*********************************************************************
            // Append to root element
            rootElement.appendChild(customerElement);
            rootElement.appendChild(orderElement);
            rootElement.appendChild(supplierElement);
            rootElement.appendChild(transporterElement);
            /////////////////////////////////////////////////////////////////////
            // WRITE TO FILE SYSTEM
            // Write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            File f = new File(fileName);
            StreamResult result = new StreamResult(f);
            transformer.transform(source, result);
            System.out.println("xml created successfully!!!");
        } catch (ParserConfigurationException e) {
            System.err.println("Error - ParserConfigurationException" + e.getMessage());
        } catch (DOMException e) {
            System.err.println("Error - DOMException" + e.getMessage());
        } catch (TransformerException e) {
            System.err.println("Error - TransformerException" + e.getMessage());
        }

    }

}

