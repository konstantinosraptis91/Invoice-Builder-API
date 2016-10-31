/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.dao;

import invoice.parser.dao.interfaces.ICustomerDao;
import invoice.parser.entity.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author konstantinos
 */
@Repository
@Qualifier("MySQLCustomer")
public class MySQLCustomerDaoImpl implements ICustomerDao {

    @Override
    public void addCustomer(Customer customer) {
        
    }

    @Override
    public Customer getCustomerById(int id) {
        return null;
    }
    
}
