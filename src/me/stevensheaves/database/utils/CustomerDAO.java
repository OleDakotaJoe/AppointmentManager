package me.stevensheaves.database.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.stevensheaves.data.model.Customer;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDAO extends DataAccessObject<Customer>{
    private final String FIND_ALL = "SELECT * FROM customers";
    private final String INSERT = "INSERT INTO customers (Customer_Name, Address, Postal_Code," +
            " Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) " +
            "VALUES ( ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP, ?, ? )";

    public ObservableList<Customer> findAll() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer(rs.getInt("Customer_ID"),rs.getString("Customer_Name"),rs.getString("Address"),rs.getString("Postal_Code"), rs.getString("Phone"), rs.getString("Created_By"),rs.getString("Last_Updated_By"), rs.getInt("Division_ID"));
                customers.add(customer);
            }
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }

        return customers;
    }

    @Override
    public Customer find(int id) {
        return null;
    }

    @Override
    public boolean update(Customer dto) {
        return false;
    }

    @Override
    public boolean create(Customer dto) {
        boolean didExecute = false;
        try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1,dto.getCustomerName());
            statement.setString(2,dto.getAddress());
            statement.setString(3,dto.getPostalCode());
            statement.setString(4,dto.getPhoneNumber());
            statement.setString(5,dto.getCreatedByUserName());
            statement.setString(6,dto.getLastUpdatedByUserName());
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

    }
}



