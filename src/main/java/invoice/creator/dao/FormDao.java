package invoice.creator.dao;

import invoice.creator.entity.Form;
import java.util.List;

/**
 *
 * @author konstantinos
 */
public interface FormDao extends Dao {
    
    public static final String TABLE_FORM = "form_";
    
    public static final String CUSTOMER_ID = CustomerDao.TABLE_CUSTOMER + "_" + ID;
    public static final String TRANSPORTER_ID = TransporterDao.TABLE_TRANSPORTER + "_" + ID;
    public static final String ORDER_ID = OrderDao.TABLE_ORDER + ID; // No underscor needed here !!!
    public static final String SUPPLIER_ID = SupplierDao.TABLE_SUPPLIER + "_" + ID;
    
    int addForm(int customerId, int orderId, int supplierId, int transporterId);
    
    Form getFormById(int id);
    
    List<Form> getForms();
    
}
