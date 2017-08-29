package invoice.parser.controller;

import invoice.parser.entity.EntityList;
import invoice.parser.entity.Form;
import invoice.parser.service.CustomerService;
import invoice.parser.service.FormService;
import invoice.parser.service.OrderService;
import invoice.parser.service.SupplierService;
import invoice.parser.service.TransporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author konstantinos
 */
@RestController
@RequestMapping("/api/form")
public class FormController {
    
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private TransporterService transporterService;
    @Autowired
    private FormService formService;
    
    @RequestMapping(method = RequestMethod.POST, 
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public void addForm(@RequestBody Form form) {
        int customerId = customerService.addCustomer(form.getCustomer());
        int orderId = orderService.addOrder(form.getOrder());
        int supplierId = supplierService.addSupplier(form.getSupplier());
        int transporterId = transporterService.addTransporter(form.getTransporter());
        formService.addForm(customerId, orderId, supplierId, transporterId);
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET, 
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Form getFormById(@PathVariable("id") int id) {
        return formService.getFormById(id);
    }
    
    @RequestMapping(method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public EntityList<Form> getForms() {
        return new EntityList<>(formService.getForms());
    }
    
}
