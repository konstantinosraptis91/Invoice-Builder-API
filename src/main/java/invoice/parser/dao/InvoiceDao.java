package invoice.parser.dao;

import invoice.parser.entity.Invoice;
import java.util.List;

/**
 *
 * @author konstantinos
 */
public interface InvoiceDao extends Dao {
    
    Invoice getInvoiceById(int id);
    
    List<Invoice> getInvoices();
    
}
