package invoice.creator.dao;

import invoice.creator.entity.Form.Customer;
import invoice.creator.entity.Invoice;
import invoice.creator.entity.ObjectFactory;
import invoice.creator.util.Constants;
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
public class MySQLCustomerDao implements CustomerDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLCustomerDao.class);
    
    @Autowired    
    private JdbcTemplate jdbcTemplate;
        
    @Override
    public int addCustomer(Customer customer) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName(TABLE_CUSTOMER).usingGeneratedKeyColumns(ID);
        Map<String, Object> params = new HashMap<>();
        params.put(FULL_NAME, customer.getFullName());
        params.put(ADDRESS, customer.getAddress());
        params.put(PHONE_NUMBER, customer.getPhoneNumber());
        params.put(EMAIL, customer.getEmail());
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
                    + TABLE_CUSTOMER + " WHERE " + ID + " = " + "'" + id + "'", 
                    (rs, rowNum) -> {
                        Customer c = objectFactory.createFormCustomer();
                        c.setFullName(rs.getString(FULL_NAME));
                        c.setAddress(rs.getString(ADDRESS));
                        c.setPhoneNumber(rs.getString(PHONE_NUMBER));
                        c.setEmail(rs.getString(EMAIL));
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
                    + TABLE_CUSTOMER + " WHERE " + ID + " = " + "'" + id + "'", 
                    (rs, rowNum) -> {
                        Invoice.ICustomer ic = new Invoice.ICustomer();
                        ic.setId(rs.getInt(ID));
                        ic.setFullName(rs.getString(FULL_NAME));
                        ic.setAddress(rs.getString(ADDRESS));
                        ic.setPhoneNumber(rs.getString(PHONE_NUMBER));
                        ic.setEmail(rs.getString(EMAIL));
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
            customers = jdbcTemplate.query("SELECT * FROM " + TABLE_CUSTOMER, 
                    (rs, rowNum) -> {
                        Customer c = objectFactory.createFormCustomer();
                        c.setFullName(rs.getString(FULL_NAME));
                        c.setAddress(rs.getString(ADDRESS));
                        c.setPhoneNumber(rs.getString(PHONE_NUMBER));
                        c.setEmail(rs.getString(EMAIL));
                        return c;
                    });
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), Constants.LOG_DATE_FORMAT.format(new Date()));
        }
        return customers;
    }
   
}
