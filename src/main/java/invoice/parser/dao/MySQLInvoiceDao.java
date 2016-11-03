/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.dao;

import invoice.parser.dao.interfaces.IInvoiceDao;
import invoice.parser.entity.Invoice;
import invoice.parser.util.Constants;
import invoice.parser.util.MySQLHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author konstantinos
 */
@Repository
@Qualifier("MySQLInvoice")
public class MySQLInvoiceDao implements IInvoiceDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLInvoiceDao.class);
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
    public Invoice getInvoiceById(int id) {
        Invoice invoice = new Invoice();
        try {
            invoice = (Invoice) jdbcTemplate.queryForObject("SELECT * FROM " 
                    + MySQLHelper.FORM_TABLE + " WHERE " 
                    + MySQLHelper.FORM_ID + " = " + "'" + id + "'", 
                    (rs, rowNum) -> {
                        Invoice i = new Invoice();
                        int formId = rs.getInt(MySQLHelper.FORM_ID);
                        Invoice.ITransporter it = mySQLTransporterDao.getITransporterById(rs.getInt(MySQLHelper.TRANSPORTER_ID));
                        Invoice.ICustomer ic = mySQLCustomerDao.getICustomerById(rs.getInt(MySQLHelper.CUSTOMER_ID));
                        Invoice.IOrder io = mySQLOrderDao.getIOrderById(rs.getInt(MySQLHelper.ORDER_ID));
                        Invoice.ISupplier is = mySQLSupplierDao.getISupplierById(rs.getInt(MySQLHelper.SUPPLIER_ID));
                        i.setId(formId);
                        i.setTransporter(it);
                        i.setCustomer(ic);
                        i.setOrder(io);
                        i.setSupplier(is);
                        return i;
                    });
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), Constants.LOG_DATE_FORMAT.format(new Date()));
        }
        return invoice;
    }

    @Override
    public List<Invoice> getInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        try {
            invoices = jdbcTemplate.query("SELECT * FROM " + MySQLHelper.FORM_TABLE, 
                    (rs, rowNum) -> {
                        Invoice i = new Invoice();
                        int formId = rs.getInt(MySQLHelper.FORM_ID);
                        Invoice.ITransporter it = mySQLTransporterDao.getITransporterById(rs.getInt(MySQLHelper.TRANSPORTER_ID));
                        Invoice.ICustomer ic = mySQLCustomerDao.getICustomerById(rs.getInt(MySQLHelper.CUSTOMER_ID));
                        Invoice.IOrder io = mySQLOrderDao.getIOrderById(rs.getInt(MySQLHelper.ORDER_ID));
                        Invoice.ISupplier is = mySQLSupplierDao.getISupplierById(rs.getInt(MySQLHelper.SUPPLIER_ID));
                        i.setId(formId);
                        i.setTransporter(it);
                        i.setCustomer(ic);
                        i.setOrder(io);
                        i.setSupplier(is);
                        return i;
                    });
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), Constants.LOG_DATE_FORMAT.format(new Date()));
        }
        return invoices;
    }
    
    
    
}
