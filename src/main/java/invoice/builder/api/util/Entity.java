package invoice.builder.api.util;

/**
 *
 * @author Konstantinos Raptis
 */
public enum Entity {
    
    CUSTOMER("c"), SUPPLIER("s"), TRANSPORTATION_COMPANY("tc");

    private final String value;

    Entity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
}
