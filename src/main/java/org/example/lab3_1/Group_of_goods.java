package org.example.lab3_1;

public class Group_of_goods {
    private int id;
    private String name;
    private String packagingType;

    // Конструктор за замовчуванням
    public Group_of_goods() {}

    // Конструктор із параметрами
    public Group_of_goods(int id, String name, String packagingType) {
        this.id = id;
        this.name = name;
        this.packagingType = packagingType;
    }

    // Геттери та сеттери
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackagingType() {
        return packagingType;
    }

    public void setPackagingType(String packagingType) {
        this.packagingType = packagingType;
    }

    @Override
    public String toString() {
        return "Group_of_goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", packagingType='" + packagingType + '\'' +
                '}';
    }
}

