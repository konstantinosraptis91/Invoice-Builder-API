/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.service;

import invoice.parser.dao.interfaces.ICustomerDao;
import invoice.parser.entity.Form.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author konstantinos
 */
@Service
public class CustomerService {
    
    @Autowired
    @Qualifier("MySQLCustomer")
    private ICustomerDao customerDao;
    
    public int addCustomer(Customer customer) {
        return customerDao.addCustomer(customer);
    }
    
    public Customer getCustomerById(int id) {
        return customerDao.getCustomerById(id);
    }
    
    public List<Customer> getCustomers() {
        return customerDao.getCustomers();
    }
    
}
