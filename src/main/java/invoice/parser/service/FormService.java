/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.service;

import invoice.parser.dao.interfaces.IFormDao;
import invoice.parser.entity.Form;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author konstantinos
 */
@Service
public class FormService {
    
    @Autowired
    @Qualifier("MySQLForm")
    private IFormDao formDao;
    
    public int addForm(int customerId, int orderId, int supplierId, int transporterId) {
        return formDao.addForm(customerId, orderId, supplierId, transporterId);
    }
    
    public Form getFormById(int id) {
        return formDao.getFormById(id);
    }
    
    public List<Form> getForms() {
        return formDao.getForms();
    }
    
}
