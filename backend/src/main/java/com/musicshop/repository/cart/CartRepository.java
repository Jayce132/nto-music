package com.musicshop.repository.cart;

import com.musicshop.model.cart.Cart;
import com.musicshop.repository.Repository;
import com.musicshop.utility.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartRepository implements Repository<Cart, Long> {

    @Override
    public Cart save(Cart cart) {
        String sql = cart.getId() == null ?
                "INSERT INTO Cart (CustomerID, DateCreated) VALUES (?, ?)" :
                "UPDATE Cart SET CustomerID = ?, DateCreated = ? WHERE CartID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, cart.getCustomerId());
            stmt.setTimestamp(2, Timestamp.valueOf(cart.getDateCreated()));
            if (cart.getId() != null) {
                stmt.setLong(3, cart.getId());
            }

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0 && cart.getId() == null) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        cart.setId(generatedKeys.getLong(1));
                    }
                }
            }
            return cart;
        } catch (SQLException e) {
            e.printStackTrace(); // Proper error handling should be added
        }
        return null;
    }

    @Override
    public Optional<Cart> findById(Long id) {
        String sql = "SELECT * FROM Cart WHERE CartID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapRowToCart(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Proper error handling should be added
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM Cart WHERE CartID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Proper error handling should be added
        }
    }

    @Override
    public List<Cart> findAll() {
        List<Cart> carts = new ArrayList<>();
        String sql = "SELECT * FROM Cart";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                carts.add(mapRowToCart(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Proper error handling should be added
        }
        return carts;
    }

    private Cart mapRowToCart(ResultSet rs) throws SQLException {
        Cart cart = new Cart();
        cart.setId(rs.getLong("CartID"));
        cart.setCustomerId(rs.getLong("CustomerID"));
        cart.setDateCreated(rs.getTimestamp("DateCreated").toLocalDateTime());
        return cart;
    }
}
