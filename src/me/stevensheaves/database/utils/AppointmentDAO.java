package me.stevensheaves.database.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.stevensheaves.data.model.Appointment;
import me.stevensheaves.data.model.CurrentUser;

import java.sql.*;
import java.time.*;

/**
 * This class follows the Data Access Object design pattern, and is used for retrieving all data from a database, that pertains to an appointment.
 */
public class AppointmentDAO extends DataAccessObject<Appointment> {
    private final String FIND_ALL = "SELECT * FROM appointments LEFT JOIN contacts ON contacts.Contact_ID = appointments.Contact_ID";
    private final String FIND_BY_ID = "SELECT * FROM appointments WHERE Appointment_ID = ?";
    private final String SELECT_LAST_INSERTED_ROW = "SELECT * FROM appointments WHERE Appointment_ID =(SELECT last_insert_id())";
    private final String DELETE = "DELETE FROM appointments WHERE Appointment_ID = ?";
    private final String FIND_BY_CUSTOMER_ID = "SELECT * FROM appointments WHERE Customer_ID = ?";
    private final String FIND_BY_USER_ID = "SELECT * FROM appointments WHERE User_ID = ?";
    private final String FIND_BY_CONTACT_ID = "SELECT * FROM appointments WHERE Contact_ID = ?";
    private final String UPDATE = "UPDATE appointments SET " +
            "Title = ?,\n" +
            "Description = ?,\n" +
            "Location = ?,\n" +
            "Type = ?,\n" +
            "Start = ?,\n" +
            "End = ?,\n" +
            "Last_Update = UTC_TIMESTAMP,\n" +
            "Last_Updated_By = ?,\n" +
            "Customer_ID =?,\n" +
            "User_ID = ?,\n" +
            "Contact_ID = ?\n" +
            "WHERE Appointment_ID = ?;\n";
    private final String INSERT = "INSERT INTO appointments\n" +
            "(\n" +
            "Title,\n" +
            "Description,\n" +
            "Location,\n" +
            "Type,\n" +
            "Start,\n" +
            "End,\n" +
            "Create_Date,\n" +
            "Created_By,\n" +
            "Last_Update,\n" +
            "Last_Updated_By,\n" +
            "Customer_ID,\n" +
            "User_ID,\n" +
            "Contact_ID)\n" +
            "VALUES\n" +
            "(?, ?, ?, ?, ?, ?, UTC_TIMESTAMP, ?, UTC_TIMESTAMP, ?, ?, ?, ?);\n";


    /**
     * Finds all <code>Appointment</code>s, and returns them in an <code>ObservableList</code>
     * @return Returns an <code>ObservableList</code> holding all <code>Appointment</code>s.
     */
    public ObservableList<Appointment> findAll() {
        ObservableList<Appointment> tempList = FXCollections.observableArrayList();
        try(PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        convertTimestampToZonedDateTime(rs.getTimestamp("Start")),
                        convertTimestampToZonedDateTime(rs.getTimestamp("End")),
                        convertTimestampToZonedDateTime(rs.getTimestamp("Create_Date")),
                        rs.getString("Created_By"),
                        convertTimestampToZonedDateTime(rs.getTimestamp("Last_Update")),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID"),
                        rs.getString("Contact_Name")
                );
                tempList.add(appointment);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return tempList;
    }

    /**
     * Queries the database for all <code>Appointment</code>s that have a foreign key that corresponds to a specific <code>Customer_ID</code>.
     * @param id The <code>Customer_ID</code> parameter in the database query.
     * @return Returns an <code>ObservableList</code> of all <code>Appointment</code>s that have a foreign key that corresponds to the specified <code>Customer_ID</code>.
     */
    public ObservableList<Appointment> findByCustomerId(int id) {
        ObservableList<Appointment> tempList = FXCollections.observableArrayList();
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_CUSTOMER_ID)) {
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        convertTimestampToZonedDateTime(rs.getTimestamp("Start")),
                        convertTimestampToZonedDateTime(rs.getTimestamp("End")),
                        convertTimestampToZonedDateTime(rs.getTimestamp("Create_Date")),
                        rs.getString("Created_By"),
                        convertTimestampToZonedDateTime(rs.getTimestamp("Last_Update")),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID")
                );
                tempList.add(appointment);
            }
        } catch(SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return tempList;
    }

    /**
     * Queries the database for the all <code>Appointment</code>s that have a foreign key that corresponds to a specific <code>User_ID</code>.
     * @param id The <code>User_ID</code> parameter in the database query.
     * @return Returns an <code>ObservableList</code> of all <code>Appointment</code>s that have a foreign key that corresponds to the specified <code>User_ID</code>.
     */
    public ObservableList<Appointment> findByUserId(int id) {
        ObservableList<Appointment> tempList = FXCollections.observableArrayList();
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_USER_ID)) {
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        convertTimestampToZonedDateTime(rs.getTimestamp("Start")),
                        convertTimestampToZonedDateTime(rs.getTimestamp("End")),
                        convertTimestampToZonedDateTime(rs.getTimestamp("Create_Date")),
                        rs.getString("Created_By"),
                        convertTimestampToZonedDateTime(rs.getTimestamp("Last_Update")),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID")
                );
                tempList.add(appointment);
            }
        } catch(SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return tempList;
    }
    /**
     * Queries the database for the  <code>Appointment</code> that has a foreign key that corresponds to a specified <code>Contact_ID</code>.
     * @param id The <code>Contact_ID</code> parameter in the database query.
     * @return Returns an <code>ObservableList</code> of all <code>Appointment</code>s that have a foreign key that corresponds to the specified <code>Contact_ID</code>.
     */
    public ObservableList<Appointment> findByContactId(int id) {
        ObservableList<Appointment> tempList = FXCollections.observableArrayList();
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_CONTACT_ID)) {
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        convertTimestampToZonedDateTime(rs.getTimestamp("Start")),
                        convertTimestampToZonedDateTime(rs.getTimestamp("End")),
                        convertTimestampToZonedDateTime(rs.getTimestamp("Create_Date")),
                        rs.getString("Created_By"),
                        convertTimestampToZonedDateTime(rs.getTimestamp("Last_Update")),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID")
                );
                tempList.add(appointment);
            }
        } catch(SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return tempList;
    }


    /**
     * Queries the database for the the <code>Appointment</code> whose primary key: <code>Appointment_ID</code> matches the supplied <code>id</code>.
     * @param id The <code>Appointment_ID</code> parameter in the database query.
     * @return Returns the <code>Appointment</code> object that corresponds to the specified <code>Appointment_ID</code>.
     */
    @Override
    public Appointment find(int id) {
        try(PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        convertTimestampToZonedDateTime(rs.getTimestamp("Start")),
                        convertTimestampToZonedDateTime(rs.getTimestamp("End")),
                        convertTimestampToZonedDateTime(rs.getTimestamp("Create_Date")),
                        rs.getString("Created_By"),
                        convertTimestampToZonedDateTime(rs.getTimestamp("Last_Update")),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID")
                );
                return appointment;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Updates the row in the database table "appointments" whose primary key: <code>Appointment_ID</code> matches the supplied <code>dto</code>'s <code>appointmentId</code>.
     * @param dto The <code>Appointment</code> object which contains all data necessary to make the successful update.
     * @return Returns true if update was successful, and false otherwise.
     */
    @Override
    public boolean update(Appointment dto) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1,dto.getTitle());
            statement.setString(2, dto.getDescription());
            statement.setString(3,dto.getLocation());
            statement.setString(4, dto.getType());
            statement.setTimestamp(5, convertZonedDateTimeToTimestamp(dto.getStartDateTime()));
            statement.setTimestamp(6, convertZonedDateTimeToTimestamp(dto.getEndDateTime()));
            statement.setString(7, CurrentUser.getUserName());
            statement.setInt(8, dto.getCustomerId());
            statement.setInt(9, dto.getUserId());
            statement.setInt(10, dto.getContactId());
            statement.setInt(11,dto.getAppointmentId());
            int numRows = statement.executeUpdate();
            if(numRows > 0) return true;
        } catch(SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * Creates row in the database table "appointments".
     * @param dto The <code>Appointment</code> object which contains all data necessary to make the successful insertion.
     * @return Returns true if insertion was successful, and false otherwise.
     */
    @Override
    public boolean create(Appointment dto) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1,dto.getTitle());
            statement.setString(2, dto.getDescription());
            statement.setString(3,dto.getLocation());
            statement.setString(4, dto.getType());
            statement.setTimestamp(5, convertZonedDateTimeToTimestamp(dto.getStartDateTime()));
            statement.setTimestamp(6, convertZonedDateTimeToTimestamp(dto.getEndDateTime()));
            statement.setString(7, CurrentUser.getUserName());
            statement.setString(8, CurrentUser.getUserName());
            statement.setInt(9, dto.getCustomerId());
            statement.setInt(10, dto.getUserId());
            statement.setInt(11,dto.getContactId());
            if(statement.executeUpdate() > 0) return true;
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    /**
     * Finds and returns the last updated row in the appointment table.
     * @return Returns an <code>Appointment</code> data transfer object, representing the last row created in the "appointments" table.
     */
    public Appointment findLast() {
        try (Statement inserted = connection.createStatement()) {
            int id;
            ResultSet rs = inserted.executeQuery(SELECT_LAST_INSERTED_ROW);
            if (rs.next()) {
                id = rs.getInt("Appointment_ID");
                return find(id);
            }
        } catch(SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     * Deletes the row in the "appointments" table associated with the supplied <code>id</code>.
     * @param id The <code>int</code> value of the id to find and delete.
     */
    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            statement.execute();
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Utility method for converting a <code>Timestamp</code> into a <code>ZonedDateTime</code> instance.
     * @param timestamp The <code>Timestamp</code> to be converted.
     * @return Returns the converted <code>ZonedDateTime</code> instance.
     */
    private ZonedDateTime convertTimestampToZonedDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime().atZone(ZoneId.systemDefault());
    }
    /**
     * Utility method for converting a <code>ZonedDateTime</code> into a <code>Timestamp</code> instance.
     * @param zonedDateTime The <code>ZonedDateTime</code> to be converted.
     * @return Returns the converted <code>Timestamp</code> instance.
     */
    private Timestamp convertZonedDateTimeToTimestamp(ZonedDateTime zonedDateTime) {
        return Timestamp.valueOf(zonedDateTime.toLocalDateTime());
    }
}
