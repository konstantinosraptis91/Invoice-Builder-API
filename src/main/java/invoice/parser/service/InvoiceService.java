/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.service;

import invoice.parser.dao.interfaces.IInvoiceDao;
import invoice.parser.entity.Invoice;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author konstantinos
 */
@Service
public class InvoiceService {
    
    @Autowired
    @Qualifier("MySQLInvoice")
    private IInvoiceDao invoiceDao;
    
    public Invoice getInvoiceById(int id) {
        return invoiceDao.getInvoiceById(id);
    }
    
    public List<Invoice> getInvoices() {
        return invoiceDao.getInvoices();
    }
    
}
