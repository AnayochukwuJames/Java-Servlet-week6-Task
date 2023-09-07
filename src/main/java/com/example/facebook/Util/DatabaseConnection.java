package com.example.facebook.Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {

    private static Connection connection;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                System.out.println("Initializing database connection...");
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/facebook_database", "root", "Gabson12@");
                System.out.println("Connected to the database.");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new SQLException("Failed to initialize database connection: Driver not found.");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new SQLException("Failed to establish database connection.");
            }
        }
        return connection;
    }


    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Database connection closed.");
        }
    }

}
