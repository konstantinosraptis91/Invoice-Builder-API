/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.entity;

import java.util.Date;

/**
 *
 * @author konstantinos
 */
public class Order {
    
    private int id;
    private String product;
    private int quantity;
    private float unitCost;
    private Date shippingDate;

    public Order() {
    }

    public Order(int id, String product, int quantity, float unitCost, Date shippingDate) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.shippingDate = shippingDate;
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(float unitCost) {
        this.unitCost = unitCost;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public float getTotalCost() {
        return quantity * unitCost;
    }
    
    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", product=" + product + ", quantity=" + quantity + ", unitCost=" + unitCost + ", shippingDate=" + shippingDate + '}';
    }
        
}
