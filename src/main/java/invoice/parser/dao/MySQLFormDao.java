/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.dao;

import invoice.parser.dao.interfaces.IFormDao;
import invoice.parser.entity.Form;
import invoice.parser.entity.Form.Customer;
import invoice.parser.entity.Form.Order;
import invoice.parser.entity.Form.Supplier;
import invoice.parser.entity.Form.Transporter;
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
@Qualifier("MySQLForm")
public class MySQLFormDao implements IFormDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLFormDao.class);
    @Autowired
    private MySQLCustomerDao mySQLCustomerDao;
    @Autowired
    private MySQLOrderDao mySQLOrderDao;
    @Autowired
    private MySQLSupplierDao mySQLSupplierDao;
    @Autowired
    private MySQLTransporterDao mySQLTransporterDao;
    
    @Autowired    
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public int addForm(int customerId, int orderId, int supplierId, int transporterId) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName(MySQLHelper.FORM_TABLE).usingGeneratedKeyColumns(MySQLHelper.FORM_ID);
        Map<String, Object> params = new HashMap<>();
        params.put(MySQLHelper.TRANSPORTER_ID, transporterId);
        params.put(MySQLHelper.CUSTOMER_ID, customerId);
        params.put(MySQLHelper.ORDER_ID, orderId);
        params.put(MySQLHelper.SUPPLIER_ID, supplierId);
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));
        LOGGER.info("form added successfully.", Constants.LOG_DATE_FORMAT.format(new Date()));
        return key.intValue();
    }

    @Override
    public Form getFormById(int id) {
        ObjectFactory objectFactory = new ObjectFactory();
        Form form = objectFactory.createForm();
        try {
            form = (Form) jdbcTemplate.queryForObject("SELECT * FROM " 
                    + MySQLHelper.FORM_TABLE + " WHERE " 
                    + MySQLHelper.FORM_ID + " = " + "'" + id + "'", 
                    (rs, rowNum) -> {
                        Form f = objectFactory.createForm();
                        Transporter t = mySQLTransporterDao.getTransporterById(rs.getInt(MySQLHelper.TRANSPORTER_ID));
                        Customer c = mySQLCustomerDao.getCustomerById(rs.getInt(MySQLHelper.CUSTOMER_ID));
                        Order o = mySQLOrderDao.getOrderById(rs.getInt(MySQLHelper.ORDER_ID));
                        Supplier s = mySQLSupplierDao.getSupplierById(rs.getInt(MySQLHelper.SUPPLIER_ID));
                        f.setTransporter(t);
                        f.setCustomer(c);
                        f.setOrder(o);
                        f.setSupplier(s);
                        return f;
                    });
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), Constants.LOG_DATE_FORMAT.format(new Date()));
        }
        return form;
    }

    @Override
    public List<Form> getForms() {
        ObjectFactory objectFactory = new ObjectFactory();
        List<Form> forms = new ArrayList<>();
        try {
            forms = jdbcTemplate.query("SELECT * FROM " + MySQLHelper.FORM_TABLE, 
                    (rs, rowNum) -> {
                        Form f = objectFactory.createForm();
                        Transporter t = mySQLTransporterDao.getTransporterById(rs.getInt(MySQLHelper.TRANSPORTER_ID));
                        Customer c = mySQLCustomerDao.getCustomerById(rs.getInt(MySQLHelper.CUSTOMER_ID));
                        Order o = mySQLOrderDao.getOrderById(rs.getInt(MySQLHelper.ORDER_ID));
                        Supplier s = mySQLSupplierDao.getSupplierById(rs.getInt(MySQLHelper.SUPPLIER_ID));
                        f.setTransporter(t);
                        f.setCustomer(c);
                        f.setOrder(o);
                        f.setSupplier(s);
                        return f;
                    });
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), Constants.LOG_DATE_FORMAT.format(new Date()));
        }
        return forms;
    }
       
}
