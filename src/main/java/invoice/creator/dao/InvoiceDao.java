package invoice.creator.dao;

import invoice.creator.entity.Invoice;
import java.util.List;

/**
 *
 * @author konstantinos
 */
public interface InvoiceDao extends Dao {
    
    Invoice getInvoiceById(int id);
    
    List<Invoice> getInvoices();
    
}
