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
public class Customer {
    
    private int id;
    private String fullName;
    private String address;
    private String phoneNumber;
    private String email;

    public Customer() {
    }

    public Customer(int id, String fullName, String address, String phoneNumber, String email) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", fullName=" + fullName + ", address=" + address + ", phoneNumber=" + phoneNumber + ", email=" + email + '}';
    }
            
}
