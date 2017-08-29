package invoice.parser.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author konstantinos
 */
@XmlRootElement(name = "invoices")
@XmlSeeAlso({Invoice.class})
public class InvoiceList {
    
    private List<Invoice> invoices;

    public InvoiceList() {
        invoices = new ArrayList<>();
    }

    public InvoiceList(List<Invoice> invoices) {
        this.invoices = invoices;
    }
    
    @XmlAnyElement
    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

}
