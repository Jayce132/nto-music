package com.musicshop.repository.cart;

import com.musicshop.model.cart.CartDetail;
import com.musicshop.utility.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartDetailRepository {

    public CartDetail save(CartDetail cartDetail) {
        String sql = cartDetail.getId() == null ?
                "INSERT INTO CartDetail (CartID, ProductID, Quantity) VALUES (?, ?, ?)" :
                "UPDATE CartDetail SET CartID = ?, ProductID = ?, Quantity = ? WHERE ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, cartDetail.getCartId());
            stmt.setLong(2, cartDetail.getProductId());
            stmt.setInt(3, cartDetail.getQuantity());
            if (cartDetail.getId() != null) {
                stmt.setLong(4, cartDetail.getId());
            }

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0 && cartDetail.getId() == null) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        cartDetail.setId(generatedKeys.getLong(1));
                    }
                }
            }
            return cartDetail;
        } catch (SQLException e) {
            // Log and handle the SQLException
            e.printStackTrace();
        }
        return null;
    }

    public Optional<CartDetail> findById(Long id) {
        String sql = "SELECT * FROM CartDetail WHERE ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapRowToCartDetail(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log and handle the SQLException
        }
        return Optional.empty();
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM CartDetail WHERE ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Log and handle the SQLException
        }
    }

    public List<CartDetail> findAll() {
        List<CartDetail> cartDetails = new ArrayList<>();
        String sql = "SELECT * FROM CartDetail";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                cartDetails.add(mapRowToCartDetail(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log and handle the SQLException
        }
        return cartDetails;
    }

    private CartDetail mapRowToCartDetail(ResultSet rs) throws SQLException {
        CartDetail cartDetail = new CartDetail();
        cartDetail.setId(rs.getLong("ID"));
        cartDetail.setCartId(rs.getLong("CartID"));
        cartDetail.setProductId(rs.getLong("ProductID"));
        cartDetail.setQuantity(rs.getInt("Quantity"));
        return cartDetail;
    }

    public Optional<CartDetail> findByCartIdAndProductId(Long cartId, Long productId) {
        String sql = "SELECT * FROM CartDetail WHERE CartID = ? AND ProductID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, cartId);
            stmt.setLong(2, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapRowToCartDetail(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log and handle the SQLException
        }
        return Optional.empty();
    }
}
