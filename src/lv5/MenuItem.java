package lv5;

public class MenuItem {
    // 속성
    private String name;
    private double price;
    private String description;

    // 생성자
    MenuItem(String name, String price, String description) {
        if (name.trim().isEmpty() || price.trim().isEmpty()) throw new IllegalArgumentException("메뉴명과 가격은 필수입니다.");
        this.name = name;
        this.price = Double.parseDouble(price);
        this.description = description;
    }

    // 기능
    // getter : 메뉴명, 가격, 설명글 출력
    String getMenuItem() {
        return name + " | W " + price + " | " + description;
    }
}