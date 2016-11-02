/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.dao;

import invoice.parser.dao.interfaces.ITransporterDao;
import invoice.parser.entity.Form.Transporter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author konstantinos
 */
@Repository
@Qualifier("MySQLTransporter")
public class MySQLTransporterDao implements ITransporterDao {

    @Override
    public void addTransporter(Transporter transporter) {
        
    }

    @Override
    public Transporter getTransporterById(int id) {
        return null;
    }
      
}
