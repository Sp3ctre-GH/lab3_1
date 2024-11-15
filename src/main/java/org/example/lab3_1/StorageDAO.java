package org.example.lab3_1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StorageDAO {

    public int addStorage(Storage storage) {
        String sql = "INSERT INTO storage (location, storage_number) VALUES ('" + storage.getLocation() + "', '" + storage.getStorageNumber() + "')";
        try (Connection conn = Database_connection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Storage getStorageById(int id) {
        String sql = "SELECT * FROM storage WHERE id = " + id;
        try (Connection conn = Database_connection.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return new Storage(
                        rs.getInt("id"),
                        rs.getString("location"),
                        rs.getString("storage_number")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Storage> getAllStorage() {
        List<Storage> storageList = new ArrayList<>();
        String sql = "SELECT * FROM storage";
        try (Connection conn = Database_connection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Storage storage = new Storage(
                        rs.getInt("id"),
                        rs.getString("location"),
                        rs.getString("storage_number")
                );
                storageList.add(storage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return storageList;
    }

    public void dropStorageTable() {
        String sql = "DROP TABLE storage";
        try (Connection conn = Database_connection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
