package invoice.builder.api.controller;

import invoice.builder.api.model.Invoice;
import invoice.builder.api.model.InvoiceList;
import invoice.builder.api.service.InvoiceService;
import invoice.builder.api.util.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable("id") int id,
            @RequestParam(value = "entity", required = true) String entity) {
        
        // customer goes here
        if (entity.equals("c")) {
            return new ResponseEntity<>(invoiceService.buildInvoiceByFormId(id, Entity.CUSTOMER), HttpStatus.OK);
        }
        
        // supplier goes here
        if (entity.equals("s")) {
            return new ResponseEntity<>(invoiceService.buildInvoiceByFormId(id, Entity.SUPPLIER), HttpStatus.OK);
        }
        
        
        // transporation company goes here
        if (entity.equals("tc")) {
            return new ResponseEntity<>(invoiceService.buildInvoiceByFormId(id, Entity.TRANSPORTATION_COMPANY), HttpStatus.OK);
        }
        
        // uknown entity
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @RequestMapping(method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<InvoiceList> getInvoices(@RequestParam(value = "entity", required = true) String entity) {
        
        InvoiceList invoiceList = new InvoiceList();
                
        // customer goes here
        if (entity.equals("c")) {
            invoiceService.buildAllInvoices(Entity.CUSTOMER).stream().forEach(i -> invoiceList.getInvoices().add(i));
            return new ResponseEntity<>(invoiceList, HttpStatus.OK);
        }
        
        // supplier goes here
        if (entity.equals("s")) {
            invoiceService.buildAllInvoices(Entity.SUPPLIER).stream().forEach(i -> invoiceList.getInvoices().add(i));
            return new ResponseEntity<>(invoiceList, HttpStatus.OK);
        }
        
        // transporation company goes here
        if (entity.equals("tc")) {
            invoiceService.buildAllInvoices(Entity.TRANSPORTATION_COMPANY).stream().forEach(i -> invoiceList.getInvoices().add(i));
            return new ResponseEntity<>(invoiceList, HttpStatus.OK);
        }
        
        // uknown entity
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
}
