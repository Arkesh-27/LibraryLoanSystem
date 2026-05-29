package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String URL =
    "jdbc:derby:LibraryStableDB;create=true";

    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(URL);
    }

}