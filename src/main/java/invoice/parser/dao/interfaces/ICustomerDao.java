/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.dao.interfaces;

import invoice.parser.entity.Form.Customer;
import invoice.parser.entity.Invoice.ICustomer;
import java.util.List;



/**
 *
 * @author konstantinos
 */
public interface ICustomerDao {
    
    int addCustomer(Customer customer);
    
    Customer getCustomerById(int id);
    
    ICustomer getICustomerById(int id);
    
    List<Customer> getCustomers();
    
}
