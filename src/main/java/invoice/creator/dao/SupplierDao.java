package invoice.creator.dao;

import invoice.creator.entity.Form.Supplier;
import invoice.creator.entity.Invoice.ISupplier;
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
