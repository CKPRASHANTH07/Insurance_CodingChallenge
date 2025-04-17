package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            String connectionString = DBPropertyUtil.getPropertyString("db.properties");
            if (connectionString == null) {
                throw new RuntimeException("Failed to retrieve connection string from db.properties");
            }
            try {
                // Ensure MySQL JDBC driver is loaded
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(connectionString);
                System.out.println("Database connection established successfully.");
            } catch (ClassNotFoundException e) {
                System.err.println("MySQL JDBC Driver not found.");
                e.printStackTrace();
                throw new RuntimeException("JDBC Driver not found", e);
            } catch (SQLException e) {
                System.err.println("Failed to connect to database: " + connectionString);
                e.printStackTrace();
                throw new RuntimeException("Database connection failed", e);
            }
        }
        return connection;
    }
}