package org.example.lab3_1;

public class Storage {
    private int id;
    private String location;
    private String storageNumber;

    // Конструктор за замовчуванням
    public Storage() {}

    // Конструктор із параметрами
    public Storage(int id, String location, String storageNumber) {
        this.id = id;
        this.location = location;
        this.storageNumber = storageNumber;
    }

    // Геттери та сеттери
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStorageNumber() {
        return storageNumber;
    }

    public void setStorageNumber(String storageNumber) {
        this.storageNumber = storageNumber;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", storageNumber='" + storageNumber + '\'' +
                '}';
    }
}
