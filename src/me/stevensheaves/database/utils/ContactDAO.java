package me.stevensheaves.database.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.stevensheaves.data.model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class follows the Data Access Object design pattern, and is used for retrieving all data from a database, that pertains to an Contact.
 */
public class ContactDAO extends DataAccessObject<Contact> {
    private final String FIND_ALL = "SELECT * FROM contacts";
    private final String INSERT = "INSERT INTO contacts (Contact_Name, Email) VALUES (?, ?);";
    private final String FIND_BY_ID = "SELECT * FROM contacts WHERE Contact_ID = ?";
    private final String UPDATE = "UPDATE contacts SET Contact_Name = ?, Email = ? WHERE Contact_ID = ?";
    private final String SELECT_LAST_INSERTED_ROW = "SELECT * FROM contacts WHERE Contact_ID =(SELECT last_insert_id())";
    private final String DELETE = "DELETE FROM contacts WHERE Contact_ID = ?";

    /**
     * Finds all <code>Contact</code>s, and returns them in an <code>ObservableList</code>
     * @return Returns an <code>ObservableList</code> holding all <code>Contact</code>s.
     */
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

    /**
     * Queries the "contacts" table whose primary key: <code>Contact_ID</code> matches the supplied <code>id</code>.
     * @param id The <code>Contact_ID</code> parameter in the database query.
     * @return Returns the <code>Contact</code> object that corresponds to the specified <code>Contact_ID</code>.
     */
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

    /**
     * Updates the row in the database table "contacts" whose primary key: <code>Contact_ID</code> matches the supplied <code>dto</code>'s <code>appointmentId</code>.
     * @param dto The <code>Contact</code> object which contains all data necessary to make the successful update.
     * @return Returns true if update was successful, and false otherwise.
     */
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

    /**
     * Creates row in the database table "contacts".
     * @param dto The <code>Contact</code> object which contains all data necessary to make the successful insertion.
     * @return Returns true if insertion was successful, and false otherwise.
     */
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

    /**
     * Finds and returns the last updated row in the appointment table.
     * @return Returns an <code>Contact</code> data transfer object, representing the last row created in the "contacts" table.
     */
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

    /**
     * Deletes the row in the "contacts" table associated with the supplied <code>id</code>.
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
}
