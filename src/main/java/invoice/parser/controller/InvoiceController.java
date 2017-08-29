package invoice.parser.controller;

import invoice.parser.entity.Invoice;
import invoice.parser.entity.InvoiceList;
import invoice.parser.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author konstantinos
 */
@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
       
    @Autowired
    private InvoiceService invoiceService;
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET, 
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Invoice getInvoiceById(@PathVariable("id") int id) {
        return invoiceService.getInvoiceById(id);
    }
    
//    @RequestMapping(method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_XML_VALUE)
//    public EntityList<Invoice> getInvoices() {
//        return new EntityList<>(invoiceService.getInvoices());
//    }
    
    @RequestMapping(method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public InvoiceList getInvoices() {
        InvoiceList invoiceList = new InvoiceList();
        invoiceService.getInvoices().stream().forEach(i -> invoiceList.getInvoices().add(i));
        return invoiceList;
    }
    
//    @RequestMapping(method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<Invoice> getInvoices() {
//        return invoiceService.getInvoices();
//    }
    
}
