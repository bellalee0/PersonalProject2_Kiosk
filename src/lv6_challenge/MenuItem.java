package lv6_challenge;

public class MenuItem {
    // 속성
    private String name;
    private double price;
    private String description;

    // 생성자
    MenuItem() {}

    MenuItem(String name, String price, String description) {
        if (name.trim().isEmpty() || price.trim().isEmpty()) throw new IllegalArgumentException("메뉴명과 가격은 필수입니다.");
        this.name = name;
        this.price = Double.parseDouble(price);
        this.description = description;
    }

    // 기능
    // toString 오버라이드
    @Override
    public String toString() {
        return name + " | W " + price + " | " + description;
    }

    void setMenuItem(String name, String price, String description) {
        if (name.trim().isEmpty() || name == null || price.trim().isEmpty() || price == null) throw new IllegalArgumentException("메뉴명과 가격은 필수입니다.");
        this.name = name;
        this.price = Double.parseDouble(price);
        this.description = description;
    }
}