/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.dao.interfaces;

import invoice.parser.entity.Supplier;

/**
 *
 * @author konstantinos
 */
public interface ISupplierDao {
    
    void addSupplier(Supplier supplier);
    
    Supplier getSupplierById(int id);
    
}
