package me.stevensheaves.data.model;

/**
 * This class is used for storing all data related to a Customer
 * There are multiple constructors in the class to account for the multiple use cases where not all data may be available or required.
 */
public class Customer {
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String createdByUserName;
    private String lastUpdatedByUserName;
    private int divisionId;
    private String divisionName;

    public Customer(int customerId, String customerName, String address, String postalCode, String phoneNumber, String createdByUserName, String lastUpdatedByUserName, int divisionId, String divisionName) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.createdByUserName = createdByUserName;
        this.lastUpdatedByUserName = lastUpdatedByUserName;
        this.divisionId = divisionId;
        this.divisionName = divisionName;
    }

    public Customer(int customerId, String customerName, String address, String postalCode, String phoneNumber, String createdByUserName, String lastUpdatedByUserName, int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.createdByUserName = createdByUserName;
        this.lastUpdatedByUserName = lastUpdatedByUserName;
        this.divisionId = divisionId;
    }

    public Customer(String customerName, String address, String postalCode, String phoneNumber, String createdByUserName, String lastUpdatedByUserName, int divisionId) {
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.createdByUserName = createdByUserName;
        this.lastUpdatedByUserName = lastUpdatedByUserName;
        this.divisionId = divisionId;
    }

    /**
     * Getter for the <code>customerId</code> field.
     * @return Returns the int value of the <code>customerId</code> field.
     */
    public int getCustomerId() {
        return customerId;
    }
    /**
     * Setter for the <code>customerId</code> field.
     * @param customerId The int value to be set as the <code>customerId</code>.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    /**
     * Getter for the <code>customerName</code> field.
     * @return Returns the String value of the <code>customerName</code> field.
     */
    public String getCustomerName() {
        return customerName;
    }
    /**
     * Setter for the <code>customerName</code> field.
     * @param customerName The int value to be set as the <code>customerName</code>.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    /**
     * Getter for the <code>address</code> field.
     * @return Returns the String value of the <code>address</code> field.
     */
    public String getAddress() {
        return address;
    }
    /**
     * Setter for the <code>address</code> field.
     * @param address The String value to be set as the <code>address</code>.
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Getter for the <code>postalCode</code> field.
     * @return Returns the String value of the <code>postalCode</code> field.
     */
    public String getPostalCode() {
        return postalCode;
    }
    /**
     * Setter for the <code>postalCode</code> field.
     * @param postalCode The int value to be set as the <code>postalCode</code>.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    /**
     * Getter for the <code>phoneNumber</code> field.
     * @return Returns the String value of the <code>phoneNumber</code> field.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Setter for the <code>phoneNumber</code> field.
     * @param phoneNumber The int value to be set as the <code>phoneNumber</code>.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * Getter for the <code>createdByUserName</code> field.
     * @return Returns the String value of the <code>createdByUserName</code> field.
     */
    public String getCreatedByUserName() {
        return createdByUserName;
    }
    /**
     * Setter for the <code>createdByUserName</code> field.
     * @param createdByUserName The int value to be set as the <code>createdByUserName</code>.
     */
    public void setCreatedByUserName(String createdByUserName) {
        this.createdByUserName = createdByUserName;
    }

    /**
     * Getter for the <code>lastUpdatedByUserName</code> field.
     * @return Returns the String value of the <code>lastUpdatedByUserName</code> field.
     */
    public String getLastUpdatedByUserName() {
        return lastUpdatedByUserName;
    }
    /**
     * Setter for the <code>lastUpdatedByUserName</code> field.
     * @param lastUpdatedByUserName The String value to be set as the <code>lastUpdatedByUserName</code>.
     */
    public void setLastUpdatedByUserName(String lastUpdatedByUserName) {
        this.lastUpdatedByUserName = lastUpdatedByUserName;
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
     * Overrides the toString() method for more readable printing.
     * @return returns the <code>customerName</code> of the Contact.
     */
    @Override
    public String toString() {
        return customerName;
    }
}
