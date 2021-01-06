package me.stevensheaves.data.model;

import me.stevensheaves.custom.utils.TimeUtilities;
import me.stevensheaves.database.utils.CustomerDAO;
import org.w3c.dom.ls.LSOutput;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is used in holding all data for an appointment.
 * There are multiple constructors in the class to account for the multiple use cases where not all data may be available or required.
 */
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


    /**
     * Getter for the  <code>appointmentId</code> field.
     * @return Returns <code>appointmentId</code>
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * Getter for the <code>title</code> field.
     * @return Returns <code>title</code>
     */
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for the  <code>description</code> field.
     * @return Returns <code>description</code>
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the  <code>location</code> field.
     * @return Returns <code>location</code>
     */
    public String getLocation() {
        return location;
    }

    /**
     * Getter for the  <code>type</code> field.
     * @return Returns <code>type</code>
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for the <code>type</code> field.
     * @param type Used for changing the <code>type</code> of the appointment.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for the  <code>startDateTime</code> field.
     * @return Returns <code>startDateTime</code>
     */
    public ZonedDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * Getter for the  <code>endDateTime</code> field.
     * @return Returns <code>endDateTime</code>
     */
    public ZonedDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * Getter for the  <code>customerId</code> field.
     * @return Returns <code>customerId</code>
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Setter for the <code>customerId</code> field.
     * @param customerId Used for changing the <code>customerId</code> of the appointment.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Getter for the  <code>userId</code> field.
     * @return Returns <code>userId</code>
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Setter for the <code>userId</code> field.
     * @param userId Used for changing the <code>userId</code> of the appointment.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Getter for the  <code>contactId</code> field.
     * @return Returns <code>contactId</code>
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Setter for the <code>contactId</code> field.
     * @param contactId Used for changing the <code>contactId</code> of the appointment.
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Getter for the  <code>contactName</code> field.
     * @return Returns <code>contactName</code>
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Setter for the <code>contactName</code> field.
     * @param contactName Used for changing the <code>contactName</code> of the appointment.
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }



    /**
     * Similar to a <code>toString()</code> method, except this formats the String to be visually appealing in the UI.
     * @return Returns a formatted <code>String</code> for use in the methods which need a <code>String</code> representation of an appointment.
     */
    public String toScheduleFormatted() {
        return "\t\tTitle: " + title + " | Appointment ID: " + appointmentId + "\n\t\t"
                + TimeUtilities.formatDate(startDateTime)
                + " - "
                + TimeUtilities.formatDate(endDateTime)
                + "\n\t\tCustomer: "
                + (new CustomerDAO().find(customerId).getCustomerName())
                + ", ID: " + customerId
                + "\n\t\tDescription: " + description;
    }

    /**
     * Overrides the <code>toString()</code> method.
     * @return Returns an easier to read toString() method.
     */
    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", title='" + title + '\'' +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                '}';
    }
}
