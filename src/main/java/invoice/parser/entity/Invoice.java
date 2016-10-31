/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.entity;

/**
 *
 * @author konstantinos
 */
public class Invoice {
    
    private Customer customer;
    private Order order;
    private Supplier supplier;
    private Transporter transporter;

    public Invoice() {
    }

    public Invoice(Customer customer, Order order, Supplier supplier, Transporter transporter) {
        this.customer = customer;
        this.order = order;
        this.supplier = supplier;
        this.transporter = transporter;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Transporter getTransporter() {
        return transporter;
    }

    public void setTransporter(Transporter transporter) {
        this.transporter = transporter;
    }

    @Override
    public String toString() {
        return "Invoice{" + "customer=" + customer + ", order=" + order + ", supplier=" + supplier + ", transporter=" + transporter + '}';
    }
        
}
