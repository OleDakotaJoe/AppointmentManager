package me.stevensheaves.data.model;

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
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCreatedByUserName() {
        return createdByUserName;
    }

    public void setCreatedByUserName(String createdByUserName) {
        this.createdByUserName = createdByUserName;
    }


    public String getLastUpdatedByUserName() {
        return lastUpdatedByUserName;
    }

    public void setLastUpdatedByUserName(String lastUpdatedByUserName) {
        this.lastUpdatedByUserName = lastUpdatedByUserName;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    @Override
    public String toString() {
        return customerName;
    }
}
