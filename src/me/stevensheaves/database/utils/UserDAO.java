package me.stevensheaves.database.utils;

import me.stevensheaves.data.model.User;
import me.stevensheaves.data.security.PasswordUtils;
import me.stevensheaves.data.security.VerifyProvidedPassword;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends DataAccessObject<User> {
    private final String FIND_BY_ID = "SELECT * FROM users WHERE  User_ID = ?";
    private final String FIND_BY_USER_NAME ="SELECT * FROM users WHERE  User_Name = ?";

    public User find(String userName){
        try(PreparedStatement statement = connection.prepareStatement(FIND_BY_USER_NAME)){
            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("User_Name"));
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
                System.out.println(rs.getString("User_Name"));
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
                String salt = rs.getString("Created_By");
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
                String salt = rs.getString("Created_By");
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
    public User update(User dto) {
        return null;
    }

    @Override
    public User create(User dto) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
