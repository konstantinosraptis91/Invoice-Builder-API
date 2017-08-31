/*
 * Generic wrapper class -> this class help controller wrap all forms in one object
 */
package invoice.creator.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author konstantinos
 */
@XmlRootElement(name = "forms")
@XmlSeeAlso({Form.class, Invoice.class})
public class EntityList<T> {

    private final List<T> listOfEntityObjects;

    public EntityList() {
        listOfEntityObjects = new ArrayList<>();
    }

    public EntityList(List<T> listOfEntityObjects) {
        this.listOfEntityObjects = listOfEntityObjects;
    }

    @XmlAnyElement
    public List<T> getItems() {
        return listOfEntityObjects;
    }

}
