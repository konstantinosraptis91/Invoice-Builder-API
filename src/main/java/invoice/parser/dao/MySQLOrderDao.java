/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.dao;

import invoice.parser.dao.interfaces.IOrderDao;
import invoice.parser.entity.Form.Order;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author konstantinos
 */
@Repository
@Qualifier("MySQLOrder")
public class MySQLOrderDao implements IOrderDao {

    @Override
    public void addOrder(Order order) {
        
    }

    @Override
    public Order getOrderById(int id) {
        return null;
    }
        
}
