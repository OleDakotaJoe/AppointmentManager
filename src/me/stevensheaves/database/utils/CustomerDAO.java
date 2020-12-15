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

public class CustomerDAO extends DataAccessObject<Customer>{
    private final String FIND_ALL = "SELECT * FROM customers LEFT JOIN first_level_divisions ON first_level_divisions.Division_ID = customers.Division_ID";
    private final String INSERT = "INSERT INTO customers (Customer_Name, Address, Postal_Code," +
            " Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) " +
            "VALUES ( ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP, ?, ? )";
    private final String UPDATE = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?,Last_Update = CURRENT_TIMESTAMP, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ? ";
    private final String DELETE = "DELETE FROM customers WHERE Customer_ID = ?";
    private final String SELECT_LAST_INSERTED_ROW = "SELECT * FROM customers WHERE Customer_ID =(SELECT last_insert_id())";
    private final String FIND_BY_ID ="SELECT * FROM customers WHERE Customer_ID = ?";

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



