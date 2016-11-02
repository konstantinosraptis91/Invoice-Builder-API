/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.util;

import invoice.parser.entity.Form.Customer;

/**
 *
 * @author konstantinos
 */
public class MySQLHelper {

    // tables
    public static final String CUSTOMER_TABLE = "_customer";
    public static final String ORDER_TABLE = "_order";
    public static final String SUPPLIER_TABLE = "_supplier";
    public static final String TRANSPORTER_TABLE = "_transporter";

    // customer table attributes
    public static final String CUSTOMER_ID = "customer_id";
    public static final String CUSTOMER_FULL_NAME = "customer_fullname";
    public static final String CUSTOMER_ADDRESS = "customer_address";
    public static final String CUSTOMER_PHONE_NUMBER = "customer_phonenumber";
    public static final String CUSTOMER_EMAIL = "customer_email";

    // order table attributes
    public static final String ORDER_ID = "order_id";
    public static final String ORDER_PRODUCT = "order_product";
    public static final String ORDER_QUANTITY = "order_quantity";
    public static final String ORDER_UNIT_COST = "order_unitcost";
    public static final String ORDER_SHIPPING_DATE = "order_shippingdate";

    // supplier table attributes
    public static final String SUPPLIER_ID = "supplier_id";
    public static final String SUPPLIER_FULL_NAME = "supplier_fullname";
    public static final String SUPPLIER_ADDRESS = "supplier_address";
    public static final String SUPPLIER_PHONE_NUMBER = "supplier_phonenumber";
    public static final String SUPPLIER_EMAIL = "supplier_email";

    // transporter table attributes
    public static final String TRANSPORTER_ID = "transporter_id";
    public static final String TRANSPORTER_NAME = "transporter_name";
    public static final String TRANSPORTER_ADDRESS = "transporter_address";
    public static final String TRANSPORTER_PHONE_NUMBER = "transporter_phonenumber";
    public static final String TRANSPORTER_EMAIL = "transporter_email";
    
}
