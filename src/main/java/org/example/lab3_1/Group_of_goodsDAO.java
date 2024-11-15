package org.example.lab3_1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Group_of_goodsDAO {

    public int addGroupOfGoods(Group_of_goods groupOfGoods) throws SQLException {
        String sql = "INSERT INTO group_of_goods (name, packaging_type) VALUES ('" + groupOfGoods.getName() + "', '" + groupOfGoods.getPackagingType() + "')";
        try (Connection conn = Database_connection.getConnection();
             Statement stmt = conn.createStatement()) {

            int affectedRows = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            if (affectedRows == 0) {
                throw new SQLException("Product group creation failed, no row added.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Повертаємо згенерований ID
                } else {
                    throw new SQLException("Product group creation failed, ID not received.");
                }
            }
        }
    }

    public Group_of_goods getGroupOfGoodsById(int id) throws SQLException {
        String sql = "SELECT * FROM group_of_goods WHERE id = " + id;
        try (Connection conn = Database_connection.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return new Group_of_goods(rs.getInt("id"), rs.getString("name"), rs.getString("packaging_type"));
            }
        }
        return null;
    }

    public void updateGroupOfGoods(Group_of_goods groupOfGoods) throws SQLException {
        String sql = "UPDATE group_of_goods SET name = '" + groupOfGoods.getName() + "', packaging_type = '" + groupOfGoods.getPackagingType() + "' WHERE id = " + groupOfGoods.getId();
        try (Connection conn = Database_connection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    public List<Group_of_goods> getAllGroupsOfGoods() throws SQLException {
        List<Group_of_goods> groupsOfGoods = new ArrayList<>();
        String sql = "SELECT * FROM group_of_goods";
        try (Connection conn = Database_connection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                groupsOfGoods.add(new Group_of_goods(rs.getInt("id"), rs.getString("name"), rs.getString("packaging_type")));
            }
        }
        return groupsOfGoods;
    }

    public void deleteGroupOfGoods(int id) throws SQLException {
        String sql = "DELETE FROM group_of_goods WHERE id = " + id;
        try (Connection conn = Database_connection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }
}
