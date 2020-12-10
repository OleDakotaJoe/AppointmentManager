package me.stevensheaves.database.utils;

import me.stevensheaves.data.model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO extends DataAccessObject<Appointment> {
    private final String FIND_ALL = "SELECT * FROM appointments";
    private final String FIND_BY_ID = "SELECT * FROM appointments WHERE Appointment_ID = ?";
    private final String UPDATE = "UPDATE appointments SET " +
            "Title = ?,\n" +
            "Description = ?,\n" +
            "Location = ?,\n" +
            "Type = ?,\n" +
            "Start = ?,\n" +
            "End = ?,\n" +
            "Last_Update = CURRENT_TIMESTAMP,\n" +
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
            "(?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?);\n";


    public List<Appointment> findAll() {
        // TODO: 12/7/2020 test this 
        List<Appointment> tempList = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        rs.getObject("Start", LocalDateTime.class),
                        rs.getObject("End", LocalDateTime.class),
                        rs.getObject("Create_Date", LocalDateTime.class),
                        rs.getString("Created_By"),
                        rs.getObject("Last_Update", LocalDateTime.class),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID")
                );
                tempList.add(appointment);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return tempList;
    }
    @Override
    public Appointment find(int id) {
        // TODO: 12/7/2020 test this function 
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
                        rs.getObject("Start", LocalDateTime.class),
                        rs.getObject("End", LocalDateTime.class),
                        rs.getObject("Create_Date", LocalDateTime.class),
                        rs.getString("Created_By"),
                        rs.getObject("Last_Update", LocalDateTime.class),
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
        return false;
    }

    @Override
    public boolean create(Appointment dto) {
        return false;
    }

    @Override
    public void delete(int id) {

    }
}
