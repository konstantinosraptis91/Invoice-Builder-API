/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.dao.interfaces;

import invoice.parser.entity.Form;
import java.util.List;

/**
 *
 * @author konstantinos
 */
public interface IFormDao {
    
    int addForm(int customerId, int orderId, int supplierId, int transporterId);
    
    Form getFormById(int id);
    
    List<Form> getForms();
    
}