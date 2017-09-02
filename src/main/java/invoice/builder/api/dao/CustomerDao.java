package invoice.builder.api.dao;

import java.util.List;
import order.form.dto.Form.Customer;

/**
 *
 * @author konstantinos
 */
public interface CustomerDao extends Dao {
    
    public static final String TABLE_CUSTOMER = "customer";
    
    int addCustomer(Customer customer);
    
    Customer getCustomerById(int id);
    
    List<Customer> getCustomers();
    
}
