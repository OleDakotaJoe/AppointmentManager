package me.stevensheaves.database.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnectionManager {
    private final String url;

    public DataBaseConnectionManager(String host, String databaseName, String port, String username, String password) {
        this.url ="jdbc:mysql://"+ host+ ":"+port+"/"+databaseName+"?user="+username+"&password="+password;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url);
    }
}
