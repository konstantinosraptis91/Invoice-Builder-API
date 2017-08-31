package invoice.creator.dao;

import invoice.creator.entity.Form.Transporter;
import invoice.creator.entity.Invoice.ITransporter;
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
