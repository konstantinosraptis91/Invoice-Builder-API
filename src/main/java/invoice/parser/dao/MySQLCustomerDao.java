/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.dao;

import invoice.parser.dao.interfaces.ICustomerDao;
import invoice.parser.entity.Form.Customer;
import invoice.parser.entity.Invoice;
import invoice.parser.entity.ObjectFactory;
import invoice.parser.util.Constants;
import invoice.parser.util.MySQLHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
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
    public int addCustomer(Customer customer) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName(MySQLHelper.CUSTOMER_TABLE).usingGeneratedKeyColumns(MySQLHelper.CUSTOMER_ID);
        Map<String, Object> params = new HashMap<>();
        params.put(MySQLHelper.CUSTOMER_FULL_NAME, customer.getFullName());
        params.put(MySQLHelper.CUSTOMER_ADDRESS, customer.getAddress());
        params.put(MySQLHelper.CUSTOMER_PHONE_NUMBER, customer.getPhoneNumber());
        params.put(MySQLHelper.CUSTOMER_EMAIL, customer.getEmail());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));
        LOGGER.info(customer + " added successfully.", Constants.LOG_DATE_FORMAT.format(new Date()));
        return key.intValue();
    }

    @Override
    public Customer getCustomerById(int id) {
        ObjectFactory objectFactory = new ObjectFactory();
        Customer customer = objectFactory.createFormCustomer();
        try {
            customer = (Customer) jdbcTemplate.queryForObject("SELECT * FROM " 
                    + MySQLHelper.CUSTOMER_TABLE + " WHERE " + MySQLHelper.CUSTOMER_ID + " = " + "'" + id + "'", 
                    (rs, rowNum) -> {
                        Customer c = objectFactory.createFormCustomer();
                        c.setFullName(rs.getString(MySQLHelper.CUSTOMER_FULL_NAME));
                        c.setAddress(rs.getString(MySQLHelper.CUSTOMER_ADDRESS));
                        c.setPhoneNumber(rs.getString(MySQLHelper.CUSTOMER_PHONE_NUMBER));
                        c.setEmail(rs.getString(MySQLHelper.CUSTOMER_EMAIL));
                        return c;
                    });
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), Constants.LOG_DATE_FORMAT.format(new Date()));
        }
        return customer;
    }

    @Override
    public Invoice.ICustomer getICustomerById(int id) {
        Invoice.ICustomer iCustomer = new Invoice.ICustomer();
        try {
            iCustomer = (Invoice.ICustomer) jdbcTemplate.queryForObject("SELECT * FROM " 
                    + MySQLHelper.CUSTOMER_TABLE + " WHERE " + MySQLHelper.CUSTOMER_ID + " = " + "'" + id + "'", 
                    (rs, rowNum) -> {
                        Invoice.ICustomer ic = new Invoice.ICustomer();
                        ic.setId(rs.getInt(MySQLHelper.CUSTOMER_ID));
                        ic.setFullName(rs.getString(MySQLHelper.CUSTOMER_FULL_NAME));
                        ic.setAddress(rs.getString(MySQLHelper.CUSTOMER_ADDRESS));
                        ic.setPhoneNumber(rs.getString(MySQLHelper.CUSTOMER_PHONE_NUMBER));
                        ic.setEmail(rs.getString(MySQLHelper.CUSTOMER_EMAIL));
                        return ic;
                    });
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), Constants.LOG_DATE_FORMAT.format(new Date()));
        }
        return iCustomer;
    }
    
    @Override
    public List<Customer> getCustomers() {
        ObjectFactory objectFactory = new ObjectFactory();
        List<Customer> customers = new ArrayList<>();
        try {
            customers = jdbcTemplate.query("SELECT * FROM " + MySQLHelper.CUSTOMER_TABLE, 
                    (rs, rowNum) -> {
                        Customer c = objectFactory.createFormCustomer();
                        c.setFullName(rs.getString(MySQLHelper.CUSTOMER_FULL_NAME));
                        c.setAddress(rs.getString(MySQLHelper.CUSTOMER_ADDRESS));
                        c.setPhoneNumber(rs.getString(MySQLHelper.CUSTOMER_PHONE_NUMBER));
                        c.setEmail(rs.getString(MySQLHelper.CUSTOMER_EMAIL));
                        return c;
                    });
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), Constants.LOG_DATE_FORMAT.format(new Date()));
        }
        return customers;
    }
   
}
