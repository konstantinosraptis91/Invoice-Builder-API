package invoice.builder.api.dao;

import java.util.List;
import order.form.dto.Form.Supplier;

/**
 *
 * @author konstantinos
 */
public interface SupplierDao extends Dao {
    
    public static final String TABLE_SUPPLIER = "supplier";
    
    int addSupplier(Supplier supplier);
    
    Supplier getSupplierById(int id);
    
    List<Supplier> getSuppliers();
    
}
