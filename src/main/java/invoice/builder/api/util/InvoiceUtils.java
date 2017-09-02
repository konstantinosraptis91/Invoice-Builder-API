package invoice.builder.api.util;

import invoice.builder.api.model.Invoice;
import order.form.dto.Form;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author konstantinos
 */
public class InvoiceUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceUtils.class);

    public static Invoice parseInvoice(Form form, Entity entity) {

        // Customer
        Invoice.InvoiceCustomer invoiceCustomer = extractCustomer(form.getCustomer(), entity);

        // Order
        Invoice.InvoiceOrder invoiceOrder = extractOrder(form.getOrder(), entity);

        // Supplier
        Invoice.InvoiceSupplier invoiceSupplier = extractSupplier(form.getSupplier(), entity);
        
        // Transporter        
        Invoice.InvoiceTransporter invoiceTransporter = extractTransporter(form.getTransporter(), entity);
        
        // Invoice
        Invoice invoice = new Invoice();
        invoice.setId(form.getId().intValue());
        invoice.setCustomer(invoiceCustomer);
        invoice.setOrder(invoiceOrder);
        invoice.setSupplier(invoiceSupplier);
        invoice.setTransporter(invoiceTransporter);
        return invoice;
    }

    private static Invoice.InvoiceCustomer extractCustomer(Form.Customer c, Entity e) {

        Invoice.InvoiceCustomer invoiceCustomer = new Invoice.InvoiceCustomer();

        invoiceCustomer.setId(c.getId());
        invoiceCustomer.setFullName(c.getFullName());
        invoiceCustomer.setAddress(c.getAddress());

        switch (e) {
            case CUSTOMER:
                invoiceCustomer.setPhoneNumber(c.getPhoneNumber());
                invoiceCustomer.setEmail(c.getEmail());
                break;
            case TRANSPORTATION_COMPANY:
                invoiceCustomer.setPhoneNumber(c.getPhoneNumber());
                // transform e-mail
                invoiceCustomer.setEmail(transformEmail(c.getEmail()));
                break;
            case SUPPLIER:
                // add phone number prefix
                invoiceCustomer.setPhoneNumber("+30" + c.getPhoneNumber());
                invoiceCustomer.setEmail(c.getEmail());
                break;
            default:
                invoiceCustomer.setPhoneNumber(c.getPhoneNumber());
                invoiceCustomer.setEmail(c.getEmail());
        }

        return invoiceCustomer;
    }

    private static Invoice.InvoiceOrder extractOrder(Form.Order o, Entity e) {

        Invoice.InvoiceOrder invoiceOrder = new Invoice.InvoiceOrder();

        invoiceOrder.setId(o.getId().intValue());
        invoiceOrder.setProduct(o.getProduct());

        int quantity = o.getQuantity().intValue();
        float unitCost = o.getUnitCost().floatValue();

        switch (e) {
            case CUSTOMER:
                invoiceOrder.setTotalCost(quantity * unitCost);
                invoiceOrder.setShippingDate(Constants.INVOICE_DATE_FORMAT.format(
                        o.getShippingDate().toGregorianCalendar().getTime()));
                break;
            case TRANSPORTATION_COMPANY:
                invoiceOrder.setTotalCost(quantity * unitCost);
                invoiceOrder.setShippingDate(o.getShippingDate().toGregorianCalendar().getTime().toString());
                break;
            case SUPPLIER:
                // change currency (we assume that exchange rates are constant)
                invoiceOrder.setTotalCost(quantity * unitCost * 1.3F);
                invoiceOrder.setShippingDate(o.getShippingDate().toGregorianCalendar().getTime().toString());
                break;
            default:
                invoiceOrder.setTotalCost(quantity * unitCost * 1.3F);
                invoiceOrder.setShippingDate(o.getShippingDate().toGregorianCalendar().getTime().toString());
        }

        return invoiceOrder;
    }

    private static Invoice.InvoiceSupplier extractSupplier(Form.Supplier s, Entity e) {
        
        Invoice.InvoiceSupplier invoiceSupplier = new Invoice.InvoiceSupplier();

        invoiceSupplier.setId(s.getId());
        invoiceSupplier.setFullName(s.getFullName());
        invoiceSupplier.setAddress(s.getAddress());
        
        switch (e) {
            case CUSTOMER:
                invoiceSupplier.setPhoneNumber(s.getPhoneNumber());
                invoiceSupplier.setEmail(s.getEmail());
                break;
            case TRANSPORTATION_COMPANY:
                invoiceSupplier.setPhoneNumber(s.getPhoneNumber());
                // transform e-mail
                invoiceSupplier.setEmail(transformEmail(s.getEmail()));
                break;
            case SUPPLIER:
                // add phone number prefix
                invoiceSupplier.setPhoneNumber(s.getPhoneNumber());
                invoiceSupplier.setEmail(s.getEmail());
                break;
            default:
                invoiceSupplier.setPhoneNumber(s.getPhoneNumber());
                invoiceSupplier.setEmail(s.getEmail());
        }

        return invoiceSupplier;
        
        
    }

    private static Invoice.InvoiceTransporter extractTransporter(Form.Transporter t, Entity e) {
        
        Invoice.InvoiceTransporter invoiceTransporter = new Invoice.InvoiceTransporter();
        
        invoiceTransporter.setId(t.getId());
        invoiceTransporter.setName(t.getName());
        invoiceTransporter.setAddress(t.getAddress());
        
        switch (e) {
            case CUSTOMER:
                invoiceTransporter.setPhoneNumber(t.getPhoneNumber());
                invoiceTransporter.setEmail(t.getEmail());
                break;
            case TRANSPORTATION_COMPANY:
                invoiceTransporter.setPhoneNumber(t.getPhoneNumber());
                // transform e-mail
                invoiceTransporter.setEmail(transformEmail(t.getEmail()));
                break;
            case SUPPLIER:
                // add phone number prefix
                invoiceTransporter.setPhoneNumber("+30" + t.getPhoneNumber());
                invoiceTransporter.setEmail(t.getEmail());
                break;
            default:
                invoiceTransporter.setPhoneNumber(t.getPhoneNumber());
                invoiceTransporter.setEmail(t.getEmail());
        }

        return invoiceTransporter;
        
    }

    private static String transformEmail(String email) {

        // at
        String[] parts = email.split("@");
        String part1 = parts[0];
        String part2 = parts[1];

        // dots
        String[] part1Parts = part1.split("\\.");
        String[] part2Parts = part2.split("\\.");

        String dot = "[dot]";
        String at = "[at]";
        
        String transformedPart1 = part1Parts[0];
        
        for (int i = 0; i < part1Parts.length; i++) {
            if (i == 0) {
                continue;
            }

            transformedPart1 += dot + part1Parts[i];
        }

        String transformedPart2 = part2Parts[0];

        for (int i = 0; i < part2Parts.length; i++) {
            if (i == 0) {
                continue;
            }

            transformedPart2 += dot + part2Parts[i];
        }

        return String.format("%s%s%s", transformedPart1, at, transformedPart2);
    }

}
