/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.dao;

import invoice.parser.dao.interfaces.ITransporterDao;
import invoice.parser.entity.Form.Transporter;
import invoice.parser.util.Constants;
import invoice.parser.util.MySQLHelper;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author konstantinos
 */
@Repository
@Qualifier("MySQLTransporter")
public class MySQLTransporterDao implements ITransporterDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLTransporterDao.class);
    
    @Autowired    
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public void addTransporter(Transporter transporter) {
        jdbcTemplate.batchUpdate("INSERT INTO " + MySQLHelper.TRANSPORTER_TABLE + " ("
            + MySQLHelper.TRANSPORTER_NAME + ","
            + MySQLHelper.TRANSPORTER_ADDRESS + ","
            + MySQLHelper.TRANSPORTER_PHONE_NUMBER + ","
            + MySQLHelper.TRANSPORTER_EMAIL + ") "
            + "VALUES ('"
            + transporter.getName() + "','"
            + transporter.getAddress() + "','" 
            + transporter.getPhoneNumber() + "','"
            + transporter.getEmail() + "')");
        LOGGER.info(transporter + " added successfully.", Constants.LOG_DATE_FORMAT.format(new Date()));
    }

    @Override
    public Transporter getTransporterById(int id) {
        return null;
    }
      
}
