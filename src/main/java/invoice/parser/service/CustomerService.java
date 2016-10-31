/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.service;

import invoice.parser.dao.interfaces.ICustomerDao;
import invoice.parser.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author konstantinos
 */
public class CustomerService {
    
    @Autowired
    @Qualifier("MySQLCustomer")
    private ICustomerDao customerDao;
    
    public void addCustomer(Customer customer) {
        customerDao.addCustomer(customer);
    }
    
    public Customer getCustomerById(int id) {
        return customerDao.getCustomerById(id);
    }
    
}
