package me.stevensheaves.database.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.stevensheaves.data.model.User;
import me.stevensheaves.data.security.VerifyProvidedPassword;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends DataAccessObject<User> {
    private final String FIND_BY_ID = "SELECT * FROM users WHERE  User_ID = ?";
    private final String FIND_BY_USER_NAME = "SELECT * FROM users WHERE  User_Name = ?";
    private final String FIND_ALL_USER_NAMES = "SELECT users.User_Name FROM users";


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

    @Override
    public boolean update(User dto) {
        return false;
    }

    @Override
    public boolean create(User dto) {
        return false;
    }

    @Override
    public void delete(int id) {
    }
}
