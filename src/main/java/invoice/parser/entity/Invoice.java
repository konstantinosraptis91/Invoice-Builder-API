/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

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
    protected Invoice.ICustomer customer;
    @XmlElement(required = true)
    protected Invoice.IOrder order;
    @XmlElement(required = true)
    protected Invoice.ISupplier supplier;
    @XmlElement(required = true)
    protected Invoice.ITransporter transporter;

    public ICustomer getCustomer() {
        return customer;
    }

    public void setCustomer(ICustomer customer) {
        this.customer = customer;
    }

    public IOrder getOrder() {
        return order;
    }

    public void setOrder(IOrder order) {
        this.order = order;
    }

    public ISupplier getSupplier() {
        return supplier;
    }

    public void setSupplier(ISupplier supplier) {
        this.supplier = supplier;
    }

    public ITransporter getTransporter() {
        return transporter;
    }

    public void setTransporter(ITransporter transporter) {
        this.transporter = transporter;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static class ICustomer extends Form.Customer {

        @XmlAttribute(required = true)
        protected int id;
                
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "product",
        "totalCost",
        "shippingDate"
    })
    public static class IOrder {

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
            return "IOrder{" + "id=" + id + ", product=" + product + ", totalCost=" + totalCost + ", shippingDate=" + shippingDate + '}';
        }

    }
    
    public static class ISupplier extends Form.Supplier {

        @XmlAttribute(required = true)
        protected int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

    }
    
    public static class ITransporter extends Form.Transporter {

        @XmlAttribute(required = true)
        protected int id;
        
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

    }

}
