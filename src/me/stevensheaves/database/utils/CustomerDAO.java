package me.stevensheaves.database.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.stevensheaves.data.model.Contact;
import me.stevensheaves.data.model.CurrentUser;
import me.stevensheaves.data.model.Customer;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.ResolverStyle;
/**
 * This class follows the Data Access Object design pattern, and is used for retrieving all data from a database, that pertains to an Customer.
 */
public class CustomerDAO extends DataAccessObject<Customer>{
    private final String FIND_ALL = "SELECT * FROM customers LEFT JOIN first_level_divisions ON first_level_divisions.Division_ID = customers.Division_ID";
    private final String INSERT = "INSERT INTO customers (Customer_Name, Address, Postal_Code," +
            " Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) " +
            "VALUES ( ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP, ?, ? )";
    private final String UPDATE = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?,Last_Update = CURRENT_TIMESTAMP, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ? ";
    private final String DELETE = "DELETE FROM customers WHERE Customer_ID = ?";
    private final String SELECT_LAST_INSERTED_ROW = "SELECT * FROM customers WHERE Customer_ID =(SELECT last_insert_id())";
    private final String FIND_BY_ID ="SELECT * FROM customers WHERE Customer_ID = ?";

    /**
     * Finds all <code>Customer</code>s, and returns them in an <code>ObservableList</code>
     * @return Returns an <code>ObservableList</code> holding all <code>Customer</code>s.
     */
    public ObservableList<Customer> findAll() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("Customer_ID"),
                        rs.getString("Customer_Name"),
                        rs.getString("Address"),
                        rs.getString("Postal_Code"),
                        rs.getString("Phone"),
                        rs.getString("Created_By"),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Division_ID"),
                        rs.getString("Division")
                );
                customers.add(customer);
            }
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }

        return customers;
    }
    
    /**
     * Queries the "customers" table whose primary key: <code>Customer_ID</code> matches the supplied <code>id</code>.
     * @param id The <code>Customer_ID</code> parameter in the database query.
     * @return Returns the <code>Customer</code> object that corresponds to the specified <code>Customer_ID</code>.
     */
    @Override
    public Customer find(int id) {

        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("Customer_ID"),
                        rs.getString("Customer_Name"),
                        rs.getString("Address"),
                        rs.getString("Postal_Code"),
                        rs.getString("Phone"),
                        rs.getString("Created_By"),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Division_ID")
                );
            }
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    /**
     * Finds and returns the last updated row in the appointment table.
     * @return Returns an <code>Customer</code> data transfer object, representing the last row created in the "customers" table.
     */
    public Customer findLast() {
        try (Statement inserted = connection.createStatement()) {
            int id;
            ResultSet rs = inserted.executeQuery(SELECT_LAST_INSERTED_ROW);
            if (rs.next()) {
                id = rs.getInt("Customer_ID");
                return find(id);
            }
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Updates the row in the database table "customers" whose primary key: <code>Customer_ID</code> matches the supplied <code>dto</code>'s <code>appointmentId</code>.
     * @param dto The <code>Customer</code> object which contains all data necessary to make the successful update.
     * @return Returns true if update was successful, and false otherwise.
     */
    @Override
    public boolean update(Customer dto) {
        boolean didUpdate = false;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1,dto.getCustomerName());
            statement.setString(2,dto.getAddress());
            statement.setString(3, dto.getPostalCode());
            statement.setString(4, dto.getPhoneNumber());
            statement.setString(5, CurrentUser.getUserName());
            statement.setInt(6, dto.getDivisionId());
            statement.setInt(7,dto.getCustomerId());
            int affectedRows = statement.executeUpdate();
            if(affectedRows > 0) {
                didUpdate = true;
            }
        } catch(SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return didUpdate;
    }
    /**
     * Creates row in the database table "customers".
     * @param dto The <code>Customer</code> object which contains all data necessary to make the successful insertion.
     * @return Returns true if insertion was successful, and false otherwise.
     */
    @Override
    public boolean create(Customer dto) {
        boolean didExecute = false;
        try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1,dto.getCustomerName());
            statement.setString(2,dto.getAddress());
            statement.setString(3,dto.getPostalCode());
            statement.setString(4,dto.getPhoneNumber());
            statement.setString(5,CurrentUser.getUserName());
            statement.setString(6,CurrentUser.getUserName());
            statement.setInt(7,dto.getDivisionId());
            if (statement.executeUpdate() > 0) {
                didExecute = true;
            }
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }

        return didExecute;
    }

    /**
     * Deletes a Customer from the database
     * @param id the id of the object to be deleted
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



