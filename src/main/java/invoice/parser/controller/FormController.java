/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.controller;

import invoice.parser.entity.Form;
import invoice.parser.service.CustomerService;
import invoice.parser.service.OrderService;
import invoice.parser.service.SupplierService;
import invoice.parser.service.TransporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author konstantinos
 */
@RestController
@RequestMapping("/form")
public class FormController {
    
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private TransporterService transporterService;
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public void addForm(@RequestBody Form form) {
        customerService.addCustomer(form.getCustomer());
        orderService.addOrder(form.getOrder());
        supplierService.addSupplier(form.getSupplier());
        transporterService.addTransporter(form.getTransporter());
    }
    
}
