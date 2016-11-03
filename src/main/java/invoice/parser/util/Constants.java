/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.util;

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
