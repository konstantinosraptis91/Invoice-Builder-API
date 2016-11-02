/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.dao.interfaces;

import invoice.parser.entity.Form.Transporter;

/**
 *
 * @author konstantinos
 */
public interface ITransporterDao {
    
    void addTransporter(Transporter transporter);
    
    Transporter getTransporterById(int id);
    
}
