package lv3;

public class MenuItem {
    // 속성
    String name;
    double price;
    String description;

    // 생성자
    MenuItem(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    // 기능
    public String toString() {
        return name + " | W " + price + " | " + description;
    }
}