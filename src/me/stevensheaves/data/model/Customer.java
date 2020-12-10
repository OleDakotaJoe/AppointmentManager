package me.stevensheaves.data.model;

import java.time.LocalDateTime;

public class Customer {
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private LocalDateTime createdDateTime;
    private String createdByUserName;
    private LocalDateTime lastUpdateDateTime;
    private String lastUpdatedByUserName;
    private int divisionID;

    public Customer(int customerId, String customerName, String address, String postalCode, String phoneNumber,
                    LocalDateTime createdDateTime, String createdByUserName, LocalDateTime lastUpdateDateTime,
                    String lastUpdatedByUserName, int divisionID) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.createdDateTime = createdDateTime;
        this.createdByUserName = createdByUserName;
        this.lastUpdateDateTime = lastUpdateDateTime;
        this.lastUpdatedByUserName = lastUpdatedByUserName;
        this.divisionID = divisionID;
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

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getCreatedByUserName() {
        return createdByUserName;
    }

    public void setCreatedByUserName(String createdByUserName) {
        this.createdByUserName = createdByUserName;
    }

    public LocalDateTime getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }

    public void setLastUpdateDateTime(LocalDateTime lastUpdateDateTime) {
        this.lastUpdateDateTime = lastUpdateDateTime;
    }

    public String getLastUpdatedByUserName() {
        return lastUpdatedByUserName;
    }

    public void setLastUpdatedByUserName(String lastUpdatedByUserName) {
        this.lastUpdatedByUserName = lastUpdatedByUserName;
    }

    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", address='" + address + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", createdDateTime=" + createdDateTime +
                ", createdByUserName='" + createdByUserName + '\'' +
                ", lastUpdateDateTime=" + lastUpdateDateTime +
                ", lastUpdatedByUserName='" + lastUpdatedByUserName + '\'' +
                ", divisionID=" + divisionID +
                '}';
    }
}
