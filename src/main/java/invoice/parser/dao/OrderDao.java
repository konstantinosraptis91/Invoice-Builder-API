package invoice.parser.dao;

import invoice.parser.entity.Form.Order;
import invoice.parser.entity.Invoice.IOrder;
import java.util.List;

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
    
    IOrder getIOrderById(int id);
    
    List<Order> getOrders();
    
}
