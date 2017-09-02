package invoice.builder.api.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import invoice.builder.api.dao.OrderDao;
import order.form.dto.Form.Order;

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
