package invoice.builder.api.dao;

import java.util.List;
import order.form.dto.Form.Order;

/**
 *
 * @author konstantinos
 */
public interface OrderDao extends Dao {
    
    public static final String TABLE_ORDER = "order_";
    
    public static final String PRODUCT = "product";
    public static final String QUANTITY = "quantity";
    public static final String UNIT_COST = "unit_cost";
    public static final String SHIPPING_DATE = "shipping_date";
    
    int addOrder(Order order);
    
    Order getOrderById(int id);
      
    List<Order> getOrders();
    
}
