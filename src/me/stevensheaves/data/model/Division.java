package me.stevensheaves.data.model;

import java.time.LocalDateTime;
/**
 * This class is used in holding all data for a Division.
 * There are multiple constructors in the class to account for the multiple use cases where not all data may be available or required.
 */
public class Division {
    private int divisionId;
    private String divisionName;
    private int countryId;

    public Division(int divisionId, String divisionName, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
    }
    /**
     * Getter for the <code>divisionId</code> field.
     * @return Returns the int value of the <code>divisionId</code> field.
     */
    public int getDivisionId() {
        return divisionId;
    }
    /**
     * Setter for the <code>divisionId</code> field.
     * @param divisionId The int value to be set as the <code>divisionId</code>.
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }
    /**
     * Getter for the <code>divisionName</code> field.
     * @return Returns the String value of the <code>divisionName</code> field.
     */
    public String getDivisionName() {
        return divisionName;
    }
    /**
     * Setter for the <code>divisionName</code> field.
     * @param divisionName The int value to be set as the <code>divisionName</code>.
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }
    /**
     * Getter for the <code>countryId</code> field.
     * @return Returns the int value of the <code>countryId</code> field.
     */
    public int getCountryId() {
        return countryId;
    }
    /**
     * Setter for the <code>countryId</code> field.
     * @param countryId The int value to be set as the <code>countryId</code>.
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    /**
     * Overrides the toString() method for more readable printing.
     * @return returns the <code>divisionName</code> of the Contact.
     */
    @Override
    public String toString() {
        return divisionName;
    }
}
