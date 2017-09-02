package invoice.builder.api.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import invoice.builder.api.dao.TransporterDao;
import order.form.dto.Form.Transporter;

/**
 *
 * @author konstantinos
 */
@Service
public class TransporterService {
    
    @Autowired
    @Qualifier("MySQLTransporter")
    private TransporterDao transporterDao;
    
    public int addTransporter(Transporter transporter) {
        return transporterDao.addTransporter(transporter);
    }
    
    public Transporter getTransporterById(int id) {
        return transporterDao.getTransporterById(id);
    }
    
    public List<Transporter> getTransporters() {
        return transporterDao.getTransporters();
    }
    
}
