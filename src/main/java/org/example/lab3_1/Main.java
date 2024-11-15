package org.example.lab3_1;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        StorageDAO storageDAO = new StorageDAO();
        Group_of_goodsDAO groupOfGoodsDAO = new Group_of_goodsDAO();
        GoodsDAO goodsDAO = new GoodsDAO();

        // Приклад додавання складу
        Storage storage = new Storage();
        storage.setLocation("City, Warehouse 1");
        storage.setStorageNumber("S123");
        int storageId = storageDAO.addStorage(storage);
        storage.setId(storageId);

        if (storage.getId() > 0) {
            // Приклад додавання групи товарів
            Group_of_goods groupOfGoods = new Group_of_goods();
            groupOfGoods.setName("Electronics");
            groupOfGoods.setPackagingType("Package");
            int groupId = groupOfGoodsDAO.addGroupOfGoods(groupOfGoods);
            groupOfGoods.setId(groupId);

            if (groupOfGoods.getId() > 0) {
                // Приклад додавання товару до групи товарів
                Goods goods = new Goods();
                goods.setName("Smartphone");
                goods.setPrice(599.99);
                goodsDAO.addGoods(goods);

                System.out.println("Product added to the database.");
            } else {
                System.out.println("Error: Failed to add the group of goods.");
            }
        } else {
            System.out.println("Error: Failed to add storage.");
        }

        // Виведення всіх складів
        System.out.println("\nList of storages:");
        for (Storage s : storageDAO.getAllStorage()) {
            System.out.println("ID: " + s.getId() + ", Location: " + s.getLocation() + ", Storage number: " + s.getStorageNumber());
        }

        // Виведення всіх груп товарів
        System.out.println("\nList of groups of goods:");
        for (Group_of_goods g : groupOfGoodsDAO.getAllGroupsOfGoods()) {
            System.out.println("ID: " + g.getId() + ", Name: " + g.getName() + ", Packaging type: " + g.getPackagingType());
        }

        // Виведення всіх товарів
        System.out.println("\nList of goods:");
        for (Goods g : goodsDAO.getAllGoods()) {
            System.out.println("ID: " + g.getId() + ", Product name: " + g.getName() + ", Price: " + g.getPrice());
        }
    }
}
