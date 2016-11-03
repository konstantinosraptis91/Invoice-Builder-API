/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.dao.interfaces;

import invoice.parser.entity.Form.Transporter;
import java.util.List;

/**
 *
 * @author konstantinos
 */
public interface ITransporterDao {
    
    int addTransporter(Transporter transporter);
    
    Transporter getTransporterById(int id);
    
    List<Transporter> getTransporters();
    
}
