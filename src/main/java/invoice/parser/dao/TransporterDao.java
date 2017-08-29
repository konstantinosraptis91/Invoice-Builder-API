package invoice.parser.dao;

import invoice.parser.entity.Form.Transporter;
import invoice.parser.entity.Invoice.ITransporter;
import java.util.List;

/**
 *
 * @author konstantinos
 */
public interface TransporterDao extends Dao {
    
    public static final String TABLE_TRANSPORTER = "transporter";
    
    public static final String NAME = "name";
    
    int addTransporter(Transporter transporter);
    
    Transporter getTransporterById(int id);
    
    ITransporter getITransporterById(int id);
    
    List<Transporter> getTransporters();
    
}
