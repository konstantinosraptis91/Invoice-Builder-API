package invoice.parser.service;

import invoice.parser.entity.Form.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import invoice.parser.dao.CustomerDao;

/**
 *
 * @author konstantinos
 */
@Service
public class CustomerService {
    
    @Autowired
    @Qualifier("MySQLCustomer")
    private CustomerDao customerDao;
    
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
