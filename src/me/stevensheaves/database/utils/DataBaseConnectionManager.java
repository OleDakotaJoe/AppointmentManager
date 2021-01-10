package me.stevensheaves.database.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Handles the configuration of the database.
 */
public class DataBaseConnectionManager {
    private final String url;

    /**
     * Constructs the url for the database.
     * @param host The database's host name
     * @param databaseName The name of the database
     * @param port The port which the database receives requests to.
     * @param username Username for the database login
     * @param password password for the database login
     */
    public DataBaseConnectionManager(String host, String databaseName, String port, String username, String password) {
        this.url ="jdbc:mysql://"+ host+ ":"+port+"/"+databaseName+"?user="+username+"&password="+password;
    }

    /**
     * Builds and returns the connection ot the database.
     * @return returns the Connection to the database.
     * @throws SQLException If the connection is unsuccessful, or has an error it throws an SQLException.
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url);
    }
}
