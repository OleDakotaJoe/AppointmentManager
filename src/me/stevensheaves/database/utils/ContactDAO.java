package me.stevensheaves.database.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.stevensheaves.data.model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContactDAO extends DataAccessObject<Contact> {
    private final String FIND_ALL = "SELECT * FROM contacts";
    private final String INSERT = "INSERT INTO contacts (Contact_Name, Email) VALUES (?, ?);";
    private final String FIND_BY_ID = "SELECT * FROM contacts WHERE Contact_ID = ?";
    private final String UPDATE = "UPDATE contacts SET Contact_Name = ?, Email = ? WHERE Contact_ID = ?";
    private final String SELECT_LAST_INSERTED_ROW = "SELECT * FROM contacts WHERE Contact_ID =(SELECT last_insert_id())";
    private final String DELETE = "DELETE FROM contacts WHERE Contact_ID = ?";

    public ObservableList<Contact> findAll() {
        ObservableList<Contact> tempList = FXCollections.observableArrayList();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL) ){
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Contact contact = new Contact(rs.getInt("Contact_ID"),rs.getString("Contact_Name"),rs.getString("Email"));
                tempList.add(contact);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return tempList;
    }

    @Override
    public Contact find(int id) {
        Contact contact = null;
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                contact = new Contact(rs.getInt("Contact_ID"),rs.getString("Contact_Name"),rs.getString("Email"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return contact;
    }

    @Override
    public boolean update(Contact dto) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, dto.getName());
            statement.setString(2, dto.getEmail());
            statement.setInt(3, dto.getId());
            int results = statement.executeUpdate();
            if (results > 0){
                return true;
            }
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean create(Contact dto) {
        try(PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, dto.getName());
            statement.setString(2, dto.getEmail());
            int results = statement.executeUpdate();
            if (results > 0){
                return true;
            }
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Contact findLast() {
        try (Statement inserted = connection.createStatement()) {
            int id;
            ResultSet rs = inserted.executeQuery(SELECT_LAST_INSERTED_ROW);
            if (rs.next()) {
                id = rs.getInt("Contact_ID");
                return find(id);
            }
        } catch(SQLException throwables) {
            throwables.printStackTrace();
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
}
