package org.example.lab3_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database_connection {

    // URL бази даних PostgreSQL
    private static final String URL = "jdbc:postgresql://localhost:5432/Tradingbase_bd";
    private static final String USER = "postgres";
    private static final String PASSWORD = "qw12as34";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Завантаження драйвера PostgreSQL
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException("PostgreSQL JDBC Driver not found.", e);
            }
            // Встановлення з'єднання з базою даних
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("The database connection is established.");
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("The database connection is closed.");
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
