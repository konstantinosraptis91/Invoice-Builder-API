package invoice.parser.util;

//import invoice.parser.entity.Form;
//import invoice.parser.entity.Invoice;
//import invoice.parser.entity.Invoice.ISupplier;
//import invoice.parser.entity.Invoice.ITransporter;
//import java.text.ParseException;
//import java.util.Date;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// *
// * @author konstantinos
// */
//public class InvoiceUtils {
//    
//    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceUtils.class);
//    
//    public static Invoice parseInvoice(Form form, int formId, int customerId,
//            int orderId, int supplierId, int transporterId) {
//        // Customer
//        Invoice.ICustomer iCustomer = new Invoice.ICustomer();
//        iCustomer.setId(customerId);
//        iCustomer.setFullName(form.getCustomer().getFullName());
//        iCustomer.setAddress(form.getCustomer().getAddress());
//        iCustomer.setPhoneNumber(form.getCustomer().getPhoneNumber());
//        iCustomer.setEmail(form.getCustomer().getEmail());
//        // Order
//        Invoice.IOrder iOrder = new Invoice.IOrder();
//        iOrder.setId(orderId);
//        iOrder.setProduct(form.getOrder().getProduct());
//        iOrder.setTotalCost(
//                // float * int = float
//                form.getOrder().getUnitCost().floatValue() * form.getOrder().getQuantity().intValue()
//        );
//        try {
//            iOrder.setShippingDate(
//                    Constants.INVOICE_DATE_FORMAT.parse(
//                            form.getOrder().getShippingDate().toGregorianCalendar().getTime().toString()
//                    )
//            );
//        } catch (ParseException e) {
//            LOGGER.error("ParseException: " + e.getMessage(), Constants.LOG_DATE_FORMAT.format(new Date()));
//        }
//        // Supplier
//        ISupplier iSupplier = new ISupplier();
//        iSupplier.setId(supplierId);
//        iSupplier.setFullName(form.getSupplier().getFullName());
//        iSupplier.setAddress(form.getSupplier().getAddress());
//        iSupplier.setPhoneNumber(form.getSupplier().getPhoneNumber());
//        iSupplier.setEmail(form.getSupplier().getEmail());
//        // Transporter        
//        ITransporter iTransporter = new ITransporter();
//        iTransporter.setId(transporterId);
//        iTransporter.setName(form.getTransporter().getName());
//        iTransporter.setAddress(form.getTransporter().getAddress());
//        iTransporter.setPhoneNumber(form.getTransporter().getPhoneNumber());
//        iTransporter.setEmail(form.getTransporter().getEmail());
//        // Invoice
//        Invoice invoice = new Invoice();
//        invoice.setId(formId);
//        invoice.setiCustomer(iCustomer);
//        invoice.setiOrder(iOrder);
//        invoice.setiSupplier(iSupplier);
//        invoice.setiTransporter(iTransporter);
//        return invoice;
//    }
//        
//}
