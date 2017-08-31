package invoice.creator.dao;

import invoice.creator.entity.Form.Customer;
import java.util.List;

/**
 *
 * @author konstantinos
 */
public interface CustomerDao extends Dao {
    
    public static final String TABLE_CUSTOMER = "customer";
    
    int addCustomer(Customer customer);
    
    Customer getCustomerById(int id);
    
    Customer getICustomerById(int id);
    
    List<Customer> getCustomers();
    
}
