package me.stevensheaves.database.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Executes a connection to a database.
 */
public class DataBaseConnectionExecutor  {
    private static final DataBaseConnectionManager dcm;
    private static Connection connection;

    static {
            dcm = new DataBaseConnectionManager("wgudb.ucertify.com", "WJ07wuU","3306","U07wuU","53689154802");
            try {
                connection = dcm.getConnection();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
    }

    public static Connection getConnection() {
        return connection;
    }
}
