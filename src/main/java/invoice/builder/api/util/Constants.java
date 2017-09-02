package invoice.builder.api.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *
 * @author konstantinos
 */
public class Constants {
    
    public static final String STORAGE_PATH = "invoice.xml";
    public static final SimpleDateFormat FORM_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat INVOICE_DATE_FORMAT = new SimpleDateFormat("dd MMMM, yyyy", Locale.US);
    public static final SimpleDateFormat LOG_DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");
    
}
