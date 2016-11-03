/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.service;

import invoice.parser.dao.interfaces.ISupplierDao;
import invoice.parser.entity.Form.Supplier;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author konstantinos
 */
@Service
public class SupplierService {
    
    @Autowired
    @Qualifier("MySQLSupplier")
    private ISupplierDao supplierDao;
    
    public int addSupplier(Supplier supplier) {
        return supplierDao.addSupplier(supplier);
    }
    
    public Supplier getSupplierById(int id) {
        return supplierDao.getSupplierById(id);
    }
    
    public List<Supplier> getSuppliers() {
        return supplierDao.getSuppliers();
    }
    
}
