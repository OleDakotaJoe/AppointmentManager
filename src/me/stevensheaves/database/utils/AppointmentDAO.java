package me.stevensheaves.database.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.stevensheaves.data.model.Appointment;
import me.stevensheaves.data.model.CurrentUser;

import java.sql.*;
import java.time.*;

public class AppointmentDAO extends DataAccessObject<Appointment> {
    private final String FIND_ALL = "SELECT * FROM appointments LEFT JOIN contacts ON contacts.Contact_ID = appointments.Contact_ID";
    private final String FIND_BY_ID = "SELECT * FROM appointments WHERE Appointment_ID = ?";
    private final String SELECT_LAST_INSERTED_ROW = "SELECT * FROM appointments WHERE Appointment_ID =(SELECT last_insert_id())";
    private final String DELETE = "DELETE FROM appointments WHERE Appointment_ID = ?";
    private final String FIND_BY_CUSTOMER_ID = "SELECT * FROM appointments WHERE Customer_ID = ?";
    private final String FIND_BY_USER_ID = "SELECT * FROM appointments WHERE User_ID = ?";
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

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            statement.execute();
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private ZonedDateTime convertTimestampToZonedDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime().atZone(ZoneId.systemDefault());
    }

    private Timestamp convertZonedDateTimeToTimestamp(ZonedDateTime zonedDateTime) {
        return Timestamp.valueOf(zonedDateTime.toLocalDateTime());
    }
}
