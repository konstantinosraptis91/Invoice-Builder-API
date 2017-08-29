package invoice.parser.dao;

import invoice.parser.entity.Form.Supplier;
import invoice.parser.entity.Invoice;
import invoice.parser.entity.ObjectFactory;
import invoice.parser.util.Constants;
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
@Qualifier("MySQLSupplier")
public class MySQLSupplierDao implements SupplierDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLSupplierDao.class);
    
    @Autowired    
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public int addSupplier(Supplier supplier) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName(TABLE_SUPPLIER).usingGeneratedKeyColumns(ID);
        Map<String, Object> params = new HashMap<>();
        params.put(FULL_NAME, supplier.getFullName());
        params.put(ADDRESS, supplier.getAddress());
        params.put(PHONE_NUMBER, supplier.getPhoneNumber());
        params.put(EMAIL, supplier.getEmail());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));
        LOGGER.info(supplier + " added successfully.", Constants.LOG_DATE_FORMAT.format(new Date()));
        return key.intValue();
    }

    @Override
    public Supplier getSupplierById(int id) {
        ObjectFactory objectFactory = new ObjectFactory();
        Supplier supplier = objectFactory.createFormSupplier();
        try {
            supplier = (Supplier) jdbcTemplate.queryForObject("SELECT * FROM " 
                    + TABLE_SUPPLIER + " WHERE " 
                    + ID + " = " + "'" + id + "'", 
                    (rs, rowNum) -> {
                        Supplier s = objectFactory.createFormSupplier();
                        s.setFullName(rs.getString(FULL_NAME));
                        s.setAddress(rs.getString(ADDRESS));
                        s.setPhoneNumber(rs.getString(PHONE_NUMBER));
                        s.setEmail(rs.getString(EMAIL));
                        return s;
                    });
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), Constants.LOG_DATE_FORMAT.format(new Date()));
        }
        return supplier;
    }

    @Override
    public Invoice.ISupplier getISupplierById(int id) {
        Invoice.ISupplier iSupplier = new Invoice.ISupplier();
        try {
            iSupplier = (Invoice.ISupplier) jdbcTemplate.queryForObject("SELECT * FROM " 
                    + TABLE_SUPPLIER + " WHERE " 
                    + ID + " = " + "'" + id + "'", 
                    (rs, rowNum) -> {
                        Invoice.ISupplier is = new Invoice.ISupplier();
                        is.setId(rs.getInt(ID));
                        is.setFullName(rs.getString(FULL_NAME));
                        is.setAddress(rs.getString(ADDRESS));
                        is.setPhoneNumber(rs.getString(PHONE_NUMBER));
                        is.setEmail(rs.getString(EMAIL));
                        return is;
                    });
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), Constants.LOG_DATE_FORMAT.format(new Date()));
        }
        return iSupplier;
    }
   
    @Override
    public List<Supplier> getSuppliers() {
        ObjectFactory objectFactory = new ObjectFactory();
        List<Supplier> suppliers = new ArrayList<>();
        try {
            suppliers = jdbcTemplate.query("SELECT * FROM " + TABLE_SUPPLIER, 
                    (rs, rowNum) -> {
                        Supplier s = objectFactory.createFormSupplier();
                        s.setFullName(rs.getString(FULL_NAME));
                        s.setAddress(rs.getString(ADDRESS));
                        s.setPhoneNumber(rs.getString(PHONE_NUMBER));
                        s.setEmail(rs.getString(EMAIL));
                        return s;
                    });
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), Constants.LOG_DATE_FORMAT.format(new Date()));
        }
        return suppliers;
    }
       
}
