/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.dao;

import invoice.parser.dao.interfaces.ISupplierDao;
import invoice.parser.entity.Form.Supplier;
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
@Qualifier("MySQLSupplier")
public class MySQLSupplierDao implements ISupplierDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLSupplierDao.class);
    
    @Autowired    
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public void addSupplier(Supplier supplier) {
        jdbcTemplate.batchUpdate("INSERT INTO " + MySQLHelper.SUPPLIER_TABLE + " ("
            + MySQLHelper.SUPPLIER_FULL_NAME + ","
            + MySQLHelper.SUPPLIER_ADDRESS + ","
            + MySQLHelper.SUPPLIER_PHONE_NUMBER + ","
            + MySQLHelper.SUPPLIER_EMAIL + ") "
            + "VALUES ('"
            + supplier.getFullName() + "','"
            + supplier.getAddress() + "','" 
            + supplier.getPhoneNumber() + "','"
            + supplier.getEmail() + "')");
        LOGGER.info(supplier + " added successfully.", Constants.LOG_DATE_FORMAT.format(new Date()));
    }

    @Override
    public Supplier getSupplierById(int id) {
        return null;
    }
       
}
