package invoice.builder.api.dao;

import java.util.List;
import order.form.dto.Form.Transporter;

/**
 *
 * @author konstantinos
 */
public interface TransporterDao extends Dao {
    
    public static final String TABLE_TRANSPORTER = "transporter";
    
    public static final String NAME = "name";
    
    int addTransporter(Transporter transporter);
    
    Transporter getTransporterById(int id);
    
    List<Transporter> getTransporters();
    
}
