package invoice.creator.dao;

import invoice.creator.entity.Invoice;
import invoice.creator.util.Constants;
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
public class MySQLInvoiceDao implements InvoiceDao {

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
                    + FormDao.TABLE_FORM + " WHERE " 
                    + FormDao.ID + " = " + "'" + id + "'", 
                    (rs, rowNum) -> {
                        Invoice i = new Invoice();
                        int formId = rs.getInt(FormDao.ID);
                        Invoice.ICustomer ic = mySQLCustomerDao.getICustomerById(rs.getInt(FormDao.CUSTOMER_ID));
                        Invoice.ITransporter it = mySQLTransporterDao.getITransporterById(rs.getInt(FormDao.TRANSPORTER_ID));
                        Invoice.IOrder io = mySQLOrderDao.getIOrderById(rs.getInt(FormDao.ORDER_ID));
                        Invoice.ISupplier is = mySQLSupplierDao.getISupplierById(rs.getInt(FormDao.SUPPLIER_ID));
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
            invoices = jdbcTemplate.query("SELECT * FROM " + FormDao.TABLE_FORM, 
                    (rs, rowNum) -> {
                        Invoice i = new Invoice();
                        int formId = rs.getInt(FormDao.ID);
                        Invoice.ICustomer ic = mySQLCustomerDao.getICustomerById(rs.getInt(FormDao.CUSTOMER_ID));
                        Invoice.ITransporter it = mySQLTransporterDao.getITransporterById(rs.getInt(FormDao.TRANSPORTER_ID));
                        Invoice.IOrder io = mySQLOrderDao.getIOrderById(rs.getInt(FormDao.ORDER_ID));
                        Invoice.ISupplier is = mySQLSupplierDao.getISupplierById(rs.getInt(FormDao.SUPPLIER_ID));
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
