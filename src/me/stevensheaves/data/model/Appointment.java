package me.stevensheaves.data.model;

import java.time.ZonedDateTime;

public class Appointment {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private ZonedDateTime startDateTime;
    private ZonedDateTime endDateTime;
    private ZonedDateTime createdTimestamp;
    private String createdByUserName;
    private ZonedDateTime lastUpdateTimestamp;
    private String lastUpdatedByUserName;
    private int customerId;
    private int userId;
    private int contactId;
    private String contactName;

    public Appointment(int appointmentId,
                       String title,
                       String description,
                       String location,
                       String type,
                       ZonedDateTime startDateTime,
                       ZonedDateTime endDateTime,
                       ZonedDateTime createdTimestamp,
                       String createdByUserName,
                       ZonedDateTime lastUpdateTimestamp,
                       String lastUpdatedByUserName,
                       int customerId,
                       int userId,
                       int contactId,
                       String contactName
    ) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.createdTimestamp = createdTimestamp;
        this.createdByUserName = createdByUserName;
        this.lastUpdateTimestamp = lastUpdateTimestamp;
        this.lastUpdatedByUserName = lastUpdatedByUserName;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.contactName = contactName;
    }

    public Appointment(int appointmentId,
                       String title,
                       String description,
                       String location,
                       String type,
                       ZonedDateTime startDateTime,
                       ZonedDateTime endDateTime,
                       ZonedDateTime createdTimestamp,
                       String createdByUserName,
                       ZonedDateTime lastUpdateTimestamp,
                       String lastUpdatedByUserName,
                       int customerId,
                       int userId,
                       int contactId
    ) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.createdTimestamp = createdTimestamp;
        this.createdByUserName = createdByUserName;
        this.lastUpdateTimestamp = lastUpdateTimestamp;
        this.lastUpdatedByUserName = lastUpdatedByUserName;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    public Appointment(String title,
                       String description,
                       String location,
                       String type,
                       ZonedDateTime startDateTime,
                       ZonedDateTime endDateTime,
                       ZonedDateTime createdTimestamp,
                       String createdByUserName,
                       ZonedDateTime lastUpdateTimestamp,
                       String lastUpdatedByUserName,
                       int customerId,
                       int userId,
                       int contactId,
                       String contactName
    ) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.createdTimestamp = createdTimestamp;
        this.createdByUserName = createdByUserName;
        this.lastUpdateTimestamp = lastUpdateTimestamp;
        this.lastUpdatedByUserName = lastUpdatedByUserName;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.contactName = contactName;
    }
    public Appointment(String title,
                       String description,
                       String location,
                       String type,
                       ZonedDateTime startDateTime,
                       ZonedDateTime endDateTime,
                       ZonedDateTime createdTimestamp,
                       String createdByUserName,
                       ZonedDateTime lastUpdateTimestamp,
                       String lastUpdatedByUserName,
                       int customerId,
                       int userId,
                       int contactId
    ) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.createdTimestamp = createdTimestamp;
        this.createdByUserName = createdByUserName;
        this.lastUpdateTimestamp = lastUpdateTimestamp;
        this.lastUpdatedByUserName = lastUpdatedByUserName;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }


    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ZonedDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(ZonedDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public ZonedDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(ZonedDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public ZonedDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(ZonedDateTime createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getCreatedByUserName() {
        return createdByUserName;
    }

    public void setCreatedByUserName(String createdByUserName) {
        this.createdByUserName = createdByUserName;
    }

    public ZonedDateTime getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    public void setLastUpdateTimestamp(ZonedDateTime lastUpdateTimestamp) {
        this.lastUpdateTimestamp = lastUpdateTimestamp;
    }

    public String getLastUpdatedByUserName() {
        return lastUpdatedByUserName;
    }

    public void setLastUpdatedByUserName(String lastUpdatedByUserName) {
        this.lastUpdatedByUserName = lastUpdatedByUserName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", createdTimestamp=" + createdTimestamp +
                ", createdByUserName='" + createdByUserName + '\'' +
                ", lastUpdateTimestamp=" + lastUpdateTimestamp +
                ", lastUpdatedByUserName='" + lastUpdatedByUserName + '\'' +
                ", customerId=" + customerId +
                ", userId=" + userId +
                ", contactId=" + contactId +
                ", contactName='" + contactName + '\'' +
                '}';
    }
}
