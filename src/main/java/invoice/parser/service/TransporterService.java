package invoice.parser.service;

import invoice.parser.entity.Form.Transporter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import invoice.parser.dao.TransporterDao;

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
