package me.stevensheaves.data.model;

/**
 * This class is used for storing all data related to a Country
 *
 */
public class Country {
    private int id;
    private String countryName;

    /**
     * @param id The <code>int</code> value to be set as the <code>Country</code>'S <code>id</code>
     * @param countryName The <code>String</code> value to be set as the <code>Country</code>'S <code>countryName</code>
     */
    public Country(int id, String countryName) {
        this.id = id;
        this.countryName = countryName;
    }

    /**
     * Getter for the <code>id</code> field.
     * @return Returns the <code>int</code> value of the <code>Country</code>'S <code>id</code>
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for the <code>id</code> field.
     * @param id Sets the <code>int</code> value of the <code>Country</code>'S <code>id</code>
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Getter for the <code>countryName</code> field.
     * @return Returns the <code>String</code> value of the <code>Country</code>'S <code>countryName</code>
     */
    public String getCountryName() {
        return countryName;
    }
    /**
     * Setter for the <code>countryName</code> field.
     * @param countryName Sets the <code>String</code> value of the <code>Country</code>'S <code>countryName</code>
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Overridden <code>toString()</code> method.
     * @return returns the <code>countryName</code>.
     */
    @Override
    public String toString() {
        return countryName;
    }
}
