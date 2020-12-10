package me.stevensheaves.data.model;

import java.time.LocalDateTime;

public class Division {
    private int divisionId;
    private String divisionName;
    private LocalDateTime createdDateTime;
    private LocalDateTime lastUpdatedDateTime;
    private int countryId;

    public Division(int divisionId, String divisionName, LocalDateTime createdDateTime, LocalDateTime lastUpdatedDateTime, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.createdDateTime = createdDateTime;
        this.lastUpdatedDateTime = lastUpdatedDateTime;
        this.countryId = countryId;
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

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public LocalDateTime getLastUpdatedDateTime() {
        return lastUpdatedDateTime;
    }

    public void setLastUpdatedDateTime(LocalDateTime lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "Division{" +
                "divisionId=" + divisionId +
                ", divisionName='" + divisionName + '\'' +
                ", createdDateTime=" + createdDateTime +
                ", lastUpdatedDateTime=" + lastUpdatedDateTime +
                ", countryId=" + countryId +
                '}';
    }
}
