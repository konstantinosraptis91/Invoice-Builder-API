package invoice.parser.dao;

import invoice.parser.entity.Form;
import invoice.parser.entity.Form.Order;
import invoice.parser.entity.Invoice;
import invoice.parser.entity.ObjectFactory;
import invoice.parser.util.Constants;
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
public class MySQLOrderDao implements OrderDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLOrderDao.class);
    
    @Autowired    
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public int addOrder(Order order) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName(TABLE_ORDER).usingGeneratedKeyColumns(ID);
        Map<String, Object> params = new HashMap<>();
        params.put(PRODUCT, order.getProduct());
        params.put(QUANTITY, order.getQuantity());
        params.put(UNIT_COST, order.getUnitCost());
        params.put(SHIPPING_DATE, order.getShippingDate().toGregorianCalendar().getTime());
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
                    + TABLE_ORDER + " WHERE " 
                    + ID + " = " + "'" + id + "'", 
                    (rs, rowNum) -> {
                        Order o = objectFactory.createFormOrder();
                        o.setProduct(rs.getString(PRODUCT));
                        o.setQuantity(new BigInteger(String.valueOf(rs.getInt(QUANTITY))));
                        o.setUnitCost(new BigDecimal(String.valueOf(rs.getFloat(UNIT_COST))));
                        // set gregorian calendar
                        try {
                            GregorianCalendar gc = new GregorianCalendar();
                            gc.setTime(rs.getDate(SHIPPING_DATE));
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
                    + TABLE_ORDER + " WHERE " 
                    + ID + " = " + "'" + id + "'", 
                    (rs, rowNum) -> {
                        Invoice.IOrder io = new Invoice.IOrder();
                        io.setId(rs.getInt(ID));
                        io.setProduct(rs.getString(PRODUCT));
                        int quantity = rs.getInt(QUANTITY);
                        float unitCost = rs.getFloat(UNIT_COST);
                        io.setTotalCost(quantity * unitCost);
                        io.setShippingDate(Constants.INVOICE_DATE_FORMAT.format(
                                rs.getDate(SHIPPING_DATE))
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
            orders = jdbcTemplate.query("SELECT * FROM " + TABLE_ORDER, 
                    (rs, rowNum) -> {
                        Order o = objectFactory.createFormOrder();
                        o.setProduct(rs.getString(PRODUCT));
                        o.setQuantity(new BigInteger(String.valueOf(rs.getInt(QUANTITY))));
                        o.setUnitCost(new BigDecimal(String.valueOf(rs.getFloat(UNIT_COST))));
                        // set gregorian calendar
                        try {
                            GregorianCalendar gc = new GregorianCalendar();
                            gc.setTime(rs.getDate(SHIPPING_DATE));
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
