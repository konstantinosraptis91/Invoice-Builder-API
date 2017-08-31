package invoice.creator.service;

import invoice.creator.entity.Invoice;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import invoice.creator.dao.InvoiceDao;

/**
 *
 * @author konstantinos
 */
@Service
public class InvoiceService {
    
    @Autowired
    @Qualifier("MySQLInvoice")
    private InvoiceDao invoiceDao;
    
    public Invoice getInvoiceById(int id) {
        return invoiceDao.getInvoiceById(id);
    }
    
    public List<Invoice> getInvoices() {
        return invoiceDao.getInvoices();
    }
    
}
