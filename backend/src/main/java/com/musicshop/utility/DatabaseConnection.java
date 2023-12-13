package com.musicshop.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Shop;encrypt=true;trustServerCertificate=true";
    private static final String USER = "";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void resetDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            // Drop tables if they exist
            stmt.execute("DROP TABLE IF EXISTS CartDetail");
            stmt.execute("DROP TABLE IF EXISTS Cart");
            stmt.execute("DROP TABLE IF EXISTS Product");

            // Recreate tables
            stmt.execute("CREATE TABLE Product (ProductID bigint IDENTITY(1,1) PRIMARY KEY, Name nvarchar(255), Description nvarchar(1000), Price decimal(18, 2), QuantityAvailable int, CategoryID bigint)");
            stmt.execute("CREATE TABLE Cart (CartID bigint IDENTITY(1,1) PRIMARY KEY, CustomerID bigint, DateCreated datetime)");
            stmt.execute("CREATE TABLE CartDetail (ID bigint IDENTITY(1,1) PRIMARY KEY, CartID bigint, ProductID bigint, Quantity int, FOREIGN KEY (CartID) REFERENCES Cart(CartID), FOREIGN KEY (ProductID) REFERENCES Product(ProductID))");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
