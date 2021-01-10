package me.stevensheaves.database.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.stevensheaves.data.model.User;
import me.stevensheaves.data.security.VerifyProvidedPassword;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class follows the Data Access Object design pattern, and is used for retrieving all data from a database, that pertains to an <code>User</code>.
 */
public class UserDAO extends DataAccessObject<User> {
    private final String FIND_BY_ID = "SELECT * FROM users WHERE  User_ID = ?";
    private final String FIND_BY_USER_NAME = "SELECT * FROM users WHERE  User_Name = ?";
    private final String FIND_ALL_USER_NAMES = "SELECT users.User_Name FROM users";

    /**
     * Finds all <code>User</code>s, and returns them in an <code>ObservableList</code>
     * @return Returns an <code>ObservableList</code> holding all <code>User</code>s.
     */
    public ObservableList<String> findAllUserNames() {
        ObservableList<String> userList = FXCollections.observableArrayList();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_USER_NAMES)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                userList.add(rs.getString("User_Name"));
            }
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    /**
     * Queries the database for the all <code>User</code>s that have a foreign key that corresponds to a specific <code>Customer_ID</code>.
     * @param userName The <code>Customer_ID</code> parameter in the database query.
     * @return Returns an <code>ObservableList</code> of all <code>User</code>s that have a foreign key that corresponds to the specified <code>Customer_ID</code>.
     */
    public User find(String userName){
        try(PreparedStatement statement = connection.prepareStatement(FIND_BY_USER_NAME)){
            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("User_ID"), rs.getString("User_Name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Queries the database for the <code>User</code> that has a foreign key that corresponds to a specific <code>Customer_ID</code>.
     * @param id The <code>User_ID</code> parameter in the database query.
     * @return Returns an <code>ObservableList</code> of all <code>User</code>s that have a foreign key that corresponds to the specified <code>User_ID</code>.
     */
    @Override
    public User find(int id) {
        try(PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("User_ID"), rs.getString("User_Name"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    /**
     * Queries the database for a user validates whether password entered, is correct.
     * @param id  The <code>User_ID</code> parameter in the database query.
     * @param password The user-provided password to be tested for validity.
     * @return Returns a <code>User</code> object, if the password was valid, and returns <code>null</code> otherwise.
     */
    public User validateUser(int id, String password) {

        try(PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString("Password");
                String salt = rs.getString("salt");
                if(VerifyProvidedPassword.verifyPassword(password,hashedPassword, salt)) {
                    return new User(rs.getInt("User_ID"), rs.getString("User_Name"));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Queries the database for a user validates whether password entered, is correct.
     * @param username  The <code>User_Name</code> parameter in the database query.
     * @param password The user-provided password to be tested for validity.
     * @return Returns a <code>User</code> object, if the password was valid, and returns <code>null</code> otherwise.
     */
    public User validateUser(String username, String password) {
        try(PreparedStatement statement = connection.prepareStatement(FIND_BY_USER_NAME)){
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString("Password");
                String salt = rs.getString("salt");
                if(VerifyProvidedPassword.verifyPassword(password,hashedPassword, salt)) {
                    return new User(rs.getInt("User_ID"), rs.getString("User_Name"));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Dummy Method
     * @param dto
     * @return
     */
    @Override
    public boolean update(User dto) {
        return false;
    }

    /**
     * Dummy Method
     * @param dto
     * @return
     */
    @Override
    public boolean create(User dto) {
        return false;
    }

    /**
     * Dummy Method
     * @param id
     */
    @Override
    public void delete(int id) {
    }
}
