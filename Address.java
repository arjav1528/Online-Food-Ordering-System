/**
 * Represents a physical address with street, city, state, and zip code information.
 * This class is immutable as all fields are final.
 */
public class Address {

    /** The street line of the address */
    private final String street;
    /** The city name of the address */
    private final String city;
    /** The state or province of the address */
    private final String state;
    /** The postal/zip code of the address */
    private final String zipCode;

    /**
     * Constructs a new Address with the specified details.
     * 
     * @param street  the street line of the address
     * @param city    the city name of the address
     * @param state   the state or province of the address
     * @param zipCode the postal/zip code of the address
     */
    public Address(String street, String city, String state, String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    /**
     * Returns the complete address as a formatted string.
     * 
     * @return a string representation of the full address in the format:
     *         "street, city, state - zipCode"
     */
    public String getFullAddress() {
        return street + ", " + city + ", " + state + " - " + zipCode;
    }
    
    /**
     * Gets the street line of this address.
     * 
     * @return the street
     */
    public String getStreet() {
        return street;
    }
    
    /**
     * Gets the city of this address.
     * 
     * @return the city
     */
    public String getCity() {
        return city;
    }
    
    /**
     * Gets the state of this address.
     * 
     * @return the state
     */
    public String getState() {
        return state;
    }
    
    /**
     * Gets the zip code of this address.
     * 
     * @return the zip code
     */
    public String getZipCode() {
        return zipCode;
    }
}
