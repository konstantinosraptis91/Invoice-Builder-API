/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.dao;

import invoice.parser.dao.interfaces.ITransporterDao;
import invoice.parser.entity.Form.Transporter;
import invoice.parser.entity.Invoice;
import invoice.parser.entity.ObjectFactory;
import invoice.parser.util.Constants;
import invoice.parser.util.MySQLHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
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
    public int addTransporter(Transporter transporter) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName(MySQLHelper.TRANSPORTER_TABLE).usingGeneratedKeyColumns(MySQLHelper.TRANSPORTER_ID);
        Map<String, Object> params = new HashMap<>();
        params.put(MySQLHelper.TRANSPORTER_NAME, transporter.getName());
        params.put(MySQLHelper.TRANSPORTER_ADDRESS, transporter.getAddress());
        params.put(MySQLHelper.TRANSPORTER_PHONE_NUMBER, transporter.getPhoneNumber());
        params.put(MySQLHelper.TRANSPORTER_EMAIL, transporter.getEmail());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));
        LOGGER.info(transporter + " added successfully.", Constants.LOG_DATE_FORMAT.format(new Date()));
        return key.intValue();
    }

    @Override
    public Transporter getTransporterById(int id) {
        ObjectFactory objectFactory = new ObjectFactory();
        Transporter transporter = objectFactory.createFormTransporter();
        try {
            transporter = (Transporter) jdbcTemplate.queryForObject("SELECT * FROM " 
                    + MySQLHelper.TRANSPORTER_TABLE + " WHERE " 
                    + MySQLHelper.TRANSPORTER_ID + " = " + "'" + id + "'", 
                    (rs, rowNum) -> {
                        Transporter t = objectFactory.createFormTransporter();
                        t.setName(rs.getString(MySQLHelper.TRANSPORTER_NAME));
                        t.setAddress(rs.getString(MySQLHelper.TRANSPORTER_ADDRESS));
                        t.setPhoneNumber(rs.getString(MySQLHelper.TRANSPORTER_PHONE_NUMBER));
                        t.setEmail(rs.getString(MySQLHelper.TRANSPORTER_EMAIL));
                        return t;
                    });
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), Constants.LOG_DATE_FORMAT.format(new Date()));
        }
        return transporter;
    }

    @Override
    public Invoice.ITransporter getITransporterById(int id) {
        Invoice.ITransporter iTransporter = new Invoice.ITransporter();
        try {
            iTransporter = (Invoice.ITransporter) jdbcTemplate.queryForObject("SELECT * FROM " 
                    + MySQLHelper.TRANSPORTER_TABLE + " WHERE " 
                    + MySQLHelper.TRANSPORTER_ID + " = " + "'" + id + "'", 
                    (rs, rowNum) -> {
                        Invoice.ITransporter it = new Invoice.ITransporter();
                        it.setId(rs.getInt(MySQLHelper.TRANSPORTER_ID));
                        it.setName(rs.getString(MySQLHelper.TRANSPORTER_NAME));
                        it.setAddress(rs.getString(MySQLHelper.TRANSPORTER_ADDRESS));
                        it.setPhoneNumber(rs.getString(MySQLHelper.TRANSPORTER_PHONE_NUMBER));
                        it.setEmail(rs.getString(MySQLHelper.TRANSPORTER_EMAIL));
                        return it;
                    });
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), Constants.LOG_DATE_FORMAT.format(new Date()));
        }
        return iTransporter;
    }
   
    @Override
    public List<Transporter> getTransporters() {
        ObjectFactory objectFactory = new ObjectFactory();
        List<Transporter> transporters = new ArrayList<>();
        try {
            transporters = jdbcTemplate.query("SELECT * FROM " + MySQLHelper.TRANSPORTER_TABLE, 
                    (rs, rowNum) -> {
                        Transporter t = objectFactory.createFormTransporter();
                        t.setName(rs.getString(MySQLHelper.TRANSPORTER_NAME));
                        t.setAddress(rs.getString(MySQLHelper.TRANSPORTER_ADDRESS));
                        t.setPhoneNumber(rs.getString(MySQLHelper.TRANSPORTER_PHONE_NUMBER));
                        t.setEmail(rs.getString(MySQLHelper.TRANSPORTER_EMAIL));
                        return t;
                    });
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), Constants.LOG_DATE_FORMAT.format(new Date()));
        }
        return transporters;
    }
      
}
