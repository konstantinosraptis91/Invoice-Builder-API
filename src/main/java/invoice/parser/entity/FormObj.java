/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.entity;

import java.util.Date;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author konstantinos
 */
@XmlRootElement(name = "form", namespace = "http://www.w3schools.com")
public class FormObj {

    // Customer Information
    @Size(min = 2, max = 30)
    private String cmFullName;
    @NotNull @Size(min = 1, max = 50)
    private String cmAddress;
    @NotNull @Size(min = 10, max = 10)
    private String cmPhoneNumber;
    @NotNull @Size(min = 1, max = 254) @Email
    private String cmEmail;

    // Supplier Information
    @Size(min = 2, max = 30)
    private String supFullName;
    @NotNull @Size(min = 1, max = 50)
    private String supAddress;
    @NotNull @Size(min = 10, max = 10)
    private String supPhoneNumber;
    @NotNull @Size(min = 1, max = 254) @Email
    private String supEmail;

    // Order Information
    @Size(min = 2, max = 30)
    private String orProductName;
    @NotNull @Min(1)
    private Integer orQuantity;
    @NotNull @DecimalMin(value = "0.01")
    private float orUnitCost;
    @NotNull @DateTimeFormat(pattern="yyyy-MM-dd") @Future
    private Date orShpippingDate;

    // Transportation Company Information
    @Size(min = 2, max = 30)
    private String tcName;
    @NotNull @Size(min = 1, max = 50)
    private String tcAddress;
    @NotNull @Size(min = 10, max = 10)
    private String tcPhoneNumber;
    @NotNull @Size(min = 1, max = 254) @Email
    private String tcEmail;

    public FormObj() {

    }

    public String getCmFullName() {
        return cmFullName;
    }

    public void setCmFullName(String cmFullName) {
        this.cmFullName = cmFullName;
    }

    public String getCmAddress() {
        return cmAddress;
    }

    public void setCmAddress(String cmAddress) {
        this.cmAddress = cmAddress;
    }

    public String getCmPhoneNumber() {
        return cmPhoneNumber;
    }

    public void setCmPhoneNumber(String cmPhoneNumber) {
        this.cmPhoneNumber = cmPhoneNumber;
    }

    public String getCmEmail() {
        return cmEmail;
    }

    public void setCmEmail(String cmEmail) {
        this.cmEmail = cmEmail;
    }

    public void setSupFullName(String supFullName) {
        this.supFullName = supFullName;
    }

    public String getSupFullName() {
        return supFullName;
    }

    public String getSupAddress() {
        return supAddress;
    }

    public void setSupAddress(String supAddress) {
        this.supAddress = supAddress;
    }

    public String getSupPhoneNumber() {
        return supPhoneNumber;
    }

    public void setSupPhoneNumber(String supPhoneNumber) {
        this.supPhoneNumber = supPhoneNumber;
    }

    public String getSupEmail() {
        return supEmail;
    }

    public void setSupEmail(String supEmail) {
        this.supEmail = supEmail;
    }

    public String getOrProductName() {
        return orProductName;
    }

    public void setOrProductName(String orProductName) {
        this.orProductName = orProductName;
    }

    public Integer getOrQuantity() {
        return orQuantity;
    }

    public void setOrQuantity(Integer orQuantity) {
        this.orQuantity = orQuantity;
    }

    public float getOrUnitCost() {
        return orUnitCost;
    }

    public void setOrUnitCost(float orUnitCost) {
        this.orUnitCost = orUnitCost;
    }

    public Date getOrShpippingDate() {
        return orShpippingDate;
    }

    public void setOrShpippingDate(Date orShpippingDate) {
        this.orShpippingDate = orShpippingDate;
    }

    public String getTcName() {
        return tcName;
    }

    public void setTcName(String tcName) {
        this.tcName = tcName;
    }

    public String getTcAddress() {
        return tcAddress;
    }

    public void setTcAddress(String tcAddress) {
        this.tcAddress = tcAddress;
    }

    public String getTcPhoneNumber() {
        return tcPhoneNumber;
    }

    public void setTcPhoneNumber(String tcPhoneNumber) {
        this.tcPhoneNumber = tcPhoneNumber;
    }
    
    public String getTcEmail() {
        return tcEmail;
    }

    public void setTcEmail(String tcEmail) {
        this.tcEmail = tcEmail;
    }

    @Override
    public String toString() {
        return "InvoiceForm{" + "cmFullName=" + cmFullName + ", cmAddress=" + cmAddress + ", cmPhoneNumber=" + cmPhoneNumber + ", cmEmail=" + cmEmail + ", supFirstName=" + supFullName + ", supAddress=" + supAddress + ", supPhoneNumber=" + supPhoneNumber + ", supEmail=" + supEmail + ", orProductName=" + orProductName + ", orQuantity=" + orQuantity + ", orUnitCost=" + orUnitCost + ", orShpippingDate=" + orShpippingDate + ", tcName=" + tcName + ", tcAddress=" + tcAddress + ", tcPhoneNumber=" + tcPhoneNumber + ", tcEmail=" + tcEmail + '}';
    }

}
