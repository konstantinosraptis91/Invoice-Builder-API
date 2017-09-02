package invoice.builder.api.service;

import invoice.builder.api.dao.FormDao;

import invoice.builder.api.model.Invoice;
import invoice.builder.api.util.Entity;
import invoice.builder.api.util.InvoiceUtils;
import java.util.List;
import java.util.stream.Collectors;
import order.form.dto.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author konstantinos
 */
@Service
public class InvoiceService {
    
    @Autowired
    @Qualifier("MySQLForm")
    private FormDao formDao;
    
    public Invoice buildInvoiceByFormId(int id, Entity e) {
        
        // retrieve form according to id
        Form theForm = formDao.getFormById(id);
        
        // create invoice according to entity param
        Invoice theInvoice = InvoiceUtils.parseInvoice(theForm, e);        
        
        return theInvoice;
    }
    
    public List<Invoice> buildAllInvoices(Entity e) {
        
        return formDao.getForms()
                .stream()
                .map(f -> InvoiceUtils.parseInvoice(f, e))
                .collect(Collectors.toList());
    }
    
}
