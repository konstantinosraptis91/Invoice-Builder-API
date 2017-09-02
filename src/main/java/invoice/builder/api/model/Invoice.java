package invoice.builder.api.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import order.form.dto.Form.Customer;
import order.form.dto.Form.Supplier;
import order.form.dto.Form.Transporter;

/**
 *
 * @author konstantinos
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "customer",
    "order",
    "supplier",
    "transporter"
})
@XmlRootElement(name = "invoice")
public class Invoice {

    @XmlAttribute(required = true)
    protected int id;
    @XmlElement(required = true)
    protected Invoice.InvoiceCustomer customer;
    @XmlElement(required = true)
    protected Invoice.InvoiceOrder order;
    @XmlElement(required = true)
    protected Invoice.InvoiceSupplier supplier;
    @XmlElement(required = true)
    protected Invoice.InvoiceTransporter transporter;

    public InvoiceCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(InvoiceCustomer customer) {
        this.customer = customer;
    }

    public InvoiceOrder getOrder() {
        return order;
    }

    public void setOrder(InvoiceOrder order) {
        this.order = order;
    }

    public InvoiceSupplier getSupplier() {
        return supplier;
    }

    public void setSupplier(InvoiceSupplier supplier) {
        this.supplier = supplier;
    }

    public InvoiceTransporter getTransporter() {
        return transporter;
    }

    public void setTransporter(InvoiceTransporter transporter) {
        this.transporter = transporter;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static class InvoiceCustomer extends Customer {

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "product",
        "totalCost",
        "shippingDate"
    })
    public static class InvoiceOrder {

        @XmlAttribute(required = true)
        protected int id;
        @XmlElement(required = true)
        protected String product;
        @XmlElement(required = true)
        protected float totalCost;
        @XmlElement(required = true)
        protected String shippingDate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public float getTotalCost() {
            return totalCost;
        }

        public void setTotalCost(float totalCost) {
            this.totalCost = totalCost;
        }

        public String getShippingDate() {
            return shippingDate;
        }

        public void setShippingDate(String shippingDate) {
            this.shippingDate = shippingDate;
        }

        @Override
        public String toString() {
            return "InvoiceOrder{" + "id=" + id + ", product=" + product + ", totalCost=" + totalCost + ", shippingDate=" + shippingDate + '}';
        }

    }

    public static class InvoiceSupplier extends Supplier {

    }

    public static class InvoiceTransporter extends Transporter {

    }

}
