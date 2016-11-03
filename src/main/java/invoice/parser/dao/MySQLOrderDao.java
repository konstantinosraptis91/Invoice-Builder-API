/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.dao;

import invoice.parser.dao.interfaces.IOrderDao;
import invoice.parser.entity.Form;
import invoice.parser.entity.Form.Order;
import invoice.parser.entity.Invoice;
import invoice.parser.entity.ObjectFactory;
import invoice.parser.util.Constants;
import invoice.parser.util.MySQLHelper;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 *
 * @author konstantinos
 */
@Repository
@Qualifier("MySQLOrder")
public class MySQLOrderDao implements IOrderDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLOrderDao.class);
    
    @Autowired    
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public int addOrder(Order order) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName(MySQLHelper.ORDER_TABLE).usingGeneratedKeyColumns(MySQLHelper.ORDER_ID);
        Map<String, Object> params = new HashMap<>();
        params.put(MySQLHelper.ORDER_PRODUCT, order.getProduct());
        params.put(MySQLHelper.ORDER_QUANTITY, order.getQuantity());
        params.put(MySQLHelper.ORDER_UNIT_COST, order.getUnitCost());
        params.put(MySQLHelper.ORDER_SHIPPING_DATE, order.getShippingDate().toGregorianCalendar().getTime());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));
        LOGGER.info(order + " added successfully.", Constants.LOG_DATE_FORMAT.format(new Date()));
        return key.intValue();
    }
   
    @Override
    public Order getOrderById(int id) {
        ObjectFactory objectFactory = new ObjectFactory();
        Order order = objectFactory.createFormOrder();
        try {
            order = (Order) jdbcTemplate.queryForObject("SELECT * FROM " 
                    + MySQLHelper.ORDER_TABLE + " WHERE " 
                    + MySQLHelper.ORDER_ID + " = " + "'" + id + "'", 
                    (rs, rowNum) -> {
                        Order o = objectFactory.createFormOrder();
                        o.setProduct(rs.getString(MySQLHelper.ORDER_PRODUCT));
                        o.setQuantity(new BigInteger(String.valueOf(rs.getInt(MySQLHelper.ORDER_QUANTITY))));
                        o.setUnitCost(new BigDecimal(String.valueOf(rs.getFloat(MySQLHelper.ORDER_UNIT_COST))));
                        // set gregorian calendar
                        try {
                            GregorianCalendar gc = new GregorianCalendar();
                            gc.setTime(rs.getDate(MySQLHelper.ORDER_SHIPPING_DATE));
                            XMLGregorianCalendar xmlgc;
                            xmlgc = DatatypeFactory
                                            .newInstance()
                                            .newXMLGregorianCalendar(gc);
                            o.setShippingDate(xmlgc);
                        } catch (DatatypeConfigurationException e) {
                            LOGGER.error("DatatypeConfigurationException: " + e.getMessage(), Constants.LOG_DATE_FORMAT.format(new Date()));
                        }
                        return o;
                    });
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), Constants.LOG_DATE_FORMAT.format(new Date()));
        }
        return order;
    }

    @Override
    public Invoice.IOrder getIOrderById(int id) {
        Invoice.IOrder iOrder = new Invoice.IOrder();
        try {
            iOrder = (Invoice.IOrder) jdbcTemplate.queryForObject("SELECT * FROM " 
                    + MySQLHelper.ORDER_TABLE + " WHERE " 
                    + MySQLHelper.ORDER_ID + " = " + "'" + id + "'", 
                    (rs, rowNum) -> {
                        Invoice.IOrder io = new Invoice.IOrder();
                        io.setId(rs.getInt(MySQLHelper.ORDER_ID));
                        io.setProduct(rs.getString(MySQLHelper.ORDER_PRODUCT));
                        int quantity = rs.getInt(MySQLHelper.ORDER_QUANTITY);
                        float unitCost = rs.getFloat(MySQLHelper.ORDER_UNIT_COST);
                        io.setTotalCost(quantity * unitCost);
                        io.setShippingDate(Constants.INVOICE_DATE_FORMAT.format(
                                rs.getDate(MySQLHelper.ORDER_SHIPPING_DATE))
                        );
                        return io;
                    });
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), Constants.LOG_DATE_FORMAT.format(new Date()));
        }
        return iOrder;
    }
   
    @Override
    public List<Order> getOrders() {
        ObjectFactory objectFactory = new ObjectFactory();
        List<Form.Order> orders = new ArrayList<>();
        try {
            orders = jdbcTemplate.query("SELECT * FROM " + MySQLHelper.ORDER_TABLE, 
                    (rs, rowNum) -> {
                        Order o = objectFactory.createFormOrder();
                        o.setProduct(rs.getString(MySQLHelper.ORDER_PRODUCT));
                        o.setQuantity(new BigInteger(String.valueOf(rs.getInt(MySQLHelper.ORDER_QUANTITY))));
                        o.setUnitCost(new BigDecimal(String.valueOf(rs.getFloat(MySQLHelper.ORDER_UNIT_COST))));
                        // set gregorian calendar
                        try {
                            GregorianCalendar gc = new GregorianCalendar();
                            gc.setTime(rs.getDate(MySQLHelper.ORDER_SHIPPING_DATE));
                            XMLGregorianCalendar xmlgc;
                            xmlgc = DatatypeFactory
                                            .newInstance()
                                            .newXMLGregorianCalendar(gc);
                            o.setShippingDate(xmlgc);
                        } catch (DatatypeConfigurationException e) {
                            LOGGER.error("DatatypeConfigurationException: " + e.getMessage(), Constants.LOG_DATE_FORMAT.format(new Date()));
                        }
                        return o;
                    });
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), Constants.LOG_DATE_FORMAT.format(new Date()));
        }
        return orders;
    }
        
}
