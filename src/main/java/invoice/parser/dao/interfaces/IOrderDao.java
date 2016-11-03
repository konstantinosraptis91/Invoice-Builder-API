/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.dao.interfaces;

import invoice.parser.entity.Form.Order;
import java.util.List;

/**
 *
 * @author konstantinos
 */
public interface IOrderDao {
    
    int addOrder(Order order);
    
    Order getOrderById(int id);
    
    List<Order> getOrders();
    
}
