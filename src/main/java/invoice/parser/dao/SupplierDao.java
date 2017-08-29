package invoice.parser.dao;

import invoice.parser.entity.Form.Supplier;
import invoice.parser.entity.Invoice.ISupplier;
import java.util.List;

/**
 *
 * @author konstantinos
 */
public interface SupplierDao extends Dao {
    
    public static final String TABLE_SUPPLIER = "supplier";
    
    int addSupplier(Supplier supplier);
    
    Supplier getSupplierById(int id);
    
    ISupplier getISupplierById(int id);
    
    List<Supplier> getSuppliers();
    
}
