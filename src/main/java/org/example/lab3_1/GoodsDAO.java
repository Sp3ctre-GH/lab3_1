package org.example.lab3_1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodsDAO {

    public int addGoods(Goods goods) throws SQLException {
        String sql = "INSERT INTO goods (name, price) VALUES ('" + goods.getName() + "', " + goods.getPrice() + ")";
        try (Connection conn = Database_connection.getConnection();
             Statement stmt = conn.createStatement()) {

            int affectedRows = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            if (affectedRows == 0) {
                throw new SQLException("Product creation failed, no row added.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Повертаємо згенерований ID
                } else {
                    throw new SQLException("Product creation failed, ID not received.");
                }
            }
        }
    }

    public Goods getGoodsById(int id) throws SQLException {
        String sql = "SELECT * FROM goods WHERE id = " + id;
        try (Connection conn = Database_connection.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return new Goods(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"));
            }
        }
        return null;
    }

    public void updateGoods(Goods goods) throws SQLException {
        String sql = "UPDATE goods SET name = '" + goods.getName() + "', price = " + goods.getPrice() + " WHERE id = " + goods.getId();
        try (Connection conn = Database_connection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    public List<Goods> getAllGoods() throws SQLException {
        List<Goods> goodsList = new ArrayList<>();
        String sql = "SELECT * FROM goods";
        try (Connection conn = Database_connection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                goodsList.add(new Goods(rs.getInt("id"), rs.getString("name"), rs.getDouble("price")));
            }
        }
        return goodsList;
    }

    public void deleteGoods(int id) throws SQLException {
        String sql = "DELETE FROM goods WHERE id = " + id;
        try (Connection conn = Database_connection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }
}
