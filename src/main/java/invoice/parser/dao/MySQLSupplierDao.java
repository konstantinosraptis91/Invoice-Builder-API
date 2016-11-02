/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.dao;

import invoice.parser.dao.interfaces.ISupplierDao;
import invoice.parser.entity.Form.Supplier;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author konstantinos
 */
@Repository
@Qualifier("MySQLSupplier")
public class MySQLSupplierDao implements ISupplierDao {

    @Override
    public void addSupplier(Supplier supplier) {
        
    }

    @Override
    public Supplier getSupplierById(int id) {
        return null;
    }
       
}
