/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.dao;

import invoice.parser.dao.interfaces.ICustomerDao;
import invoice.parser.entity.Form.Customer;
import invoice.parser.util.Constants;
import invoice.parser.util.MySQLHelper;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author konstantinos
 */
@Repository
@Qualifier("MySQLCustomer")
public class MySQLCustomerDao implements ICustomerDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLCustomerDao.class);
    
    @Autowired    
    private JdbcTemplate jdbcTemplate;
        
    @Override
    public void addCustomer(Customer customer) {
        jdbcTemplate.batchUpdate("INSERT INTO " + MySQLHelper.CUSTOMER_TABLE + " ("
            + MySQLHelper.CUSTOMER_FULL_NAME + ","
            + MySQLHelper.CUSTOMER_ADDRESS + ","
            + MySQLHelper.CUSTOMER_PHONE_NUMBER + ","
            + MySQLHelper.CUSTOMER_EMAIL + ") "
            + "VALUES ('"
            + customer.getFullName() + "','"
            + customer.getAddress() + "','" 
            + customer.getPhoneNumber() + "','"
            + customer.getEmail() + "')");
        LOGGER.info(customer + " added successfully.", Constants.LOG_DATE_FORMAT.format(new Date()));
    }

    @Override
    public Customer getCustomerById(int id) {
        return null;
    }
    
}
