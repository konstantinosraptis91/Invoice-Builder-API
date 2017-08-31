package invoice.creator.service;

import invoice.creator.entity.Form.Order;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import invoice.creator.dao.OrderDao;

/**
 *
 * @author konstantinos
 */
@Service
public class OrderService {
    
    @Autowired
    @Qualifier("MySQLOrder")
    private OrderDao orderDao;
    
    public int addOrder(Order order) {
        return orderDao.addOrder(order);
    }
    
    public Order getOrderById(int id) {
        return orderDao.getOrderById(id);
    }
    
    public List<Order> getOrders() {
        return orderDao.getOrders();
    }
    
}
