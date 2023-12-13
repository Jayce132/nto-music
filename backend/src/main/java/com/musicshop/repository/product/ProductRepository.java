package com.musicshop.repository.product;

import com.musicshop.model.product.Product;
import com.musicshop.repository.Repository;
import com.musicshop.utility.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository implements Repository<Product, Long> {

    @Override
    public Product save(Product product) {
        String sql = product.getId() == null ?
                "INSERT INTO Product (Name, Description, Price, QuantityAvailable, CategoryID) VALUES (?, ?, ?, ?, ?)" :
                "UPDATE Product SET Name = ?, Description = ?, Price = ?, QuantityAvailable = ?, CategoryID = ? WHERE ProductID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setBigDecimal(3, product.getPrice());
            stmt.setInt(4, product.getQuantityAvailable());
            stmt.setLong(5, product.getCategoryId());
            if (product.getId() != null) {
                stmt.setLong(6, product.getId());
            }

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0 && product.getId() == null) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        product.setId(generatedKeys.getLong(1));
                    }
                }
            }
            return product;
        } catch (SQLException e) {
            e.printStackTrace(); // Proper error handling should be added
        }
        return null;
    }

    @Override
    public Optional<Product> findById(Long id) {
        String sql = "SELECT * FROM Product WHERE ProductID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapRowToProduct(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Proper error handling should be added
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM Product WHERE ProductID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Proper error handling should be added
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                products.add(mapRowToProduct(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Proper error handling should be added
        }
        return products;
    }

    private Product mapRowToProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong("ProductID"));
        product.setName(rs.getString("Name"));
        product.setDescription(rs.getString("Description"));
        product.setPrice(rs.getBigDecimal("Price"));
        product.setQuantityAvailable(rs.getInt("QuantityAvailable"));
        product.setCategoryId(rs.getLong("CategoryID"));
        return product;
    }
}
