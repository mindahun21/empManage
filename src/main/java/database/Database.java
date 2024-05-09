package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    final String  url ="jdbc:mysql://localhost:3306/demo";
    final String username = "root";
    final String password = "root";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}