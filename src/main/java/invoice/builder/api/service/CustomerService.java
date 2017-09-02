package invoice.builder.api.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import invoice.builder.api.dao.CustomerDao;
import order.form.dto.Form.Customer;

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
