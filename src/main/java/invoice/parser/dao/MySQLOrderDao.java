/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.dao;

import invoice.parser.dao.interfaces.IOrderDao;
import invoice.parser.entity.Form.Order;
import invoice.parser.util.Constants;
import invoice.parser.util.MySQLHelper;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public void addOrder(Order order) {
        jdbcTemplate.batchUpdate("INSERT INTO " + MySQLHelper.ORDER_TABLE + " ("
            + MySQLHelper.ORDER_PRODUCT + ","
            + MySQLHelper.ORDER_QUANTITY + ","
            + MySQLHelper.ORDER_UNIT_COST + ","
            + MySQLHelper.ORDER_SHIPPING_DATE + ") "
            + "VALUES ('"
            + order.getProduct() + "','"
            + order.getQuantity() + "','" 
            + order.getUnitCost() + "','"
            + order.getShippingDate() + "')");
        LOGGER.info(order + " added successfully.", Constants.LOG_DATE_FORMAT.format(new Date()));
    }

    @Override
    public Order getOrderById(int id) {
        return null;
    }
        
}
