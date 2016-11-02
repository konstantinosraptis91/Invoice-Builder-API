/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.service;

import invoice.parser.dao.interfaces.IOrderDao;
import invoice.parser.entity.Form.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author konstantinos
 */
@Service
public class OrderService {
    
    @Autowired
    @Qualifier("MySQLOrder")
    private IOrderDao orderDao;
    
    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }
    
    public Order getOrderById(int id) {
        return orderDao.getOrderById(id);
    }
    
}
