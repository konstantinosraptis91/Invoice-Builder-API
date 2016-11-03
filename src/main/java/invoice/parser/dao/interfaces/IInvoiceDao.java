/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.dao.interfaces;

import invoice.parser.entity.Invoice;
import java.util.List;

/**
 *
 * @author konstantinos
 */
public interface IInvoiceDao {
    
    Invoice getInvoiceById(int id);
    
    List<Invoice> getInvoices();
    
}
