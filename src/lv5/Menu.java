package lv5;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    // 속성
    List<MenuItem> menuItems;
    String categoryName;

    // 생성자(오버라이드)
    Menu(String categoryName) {
        this.categoryName = categoryName;
        this.menuItems = new ArrayList<>();
    }

    // toString 오버라이드
    @Override
    public String toString() { return categoryName.toUpperCase(); }

    // MenuItem 객체를 리스트에 저작
    void saveMenus(List<MenuItem> menus) { this.menuItems = new ArrayList<>(menus); }

    // 리스트에 있는 MenuItem을 순차적으로 프린트
    void printMenus() {
        for (int i = 1; i <= menuItems.size(); i++) {
            System.out.println(i + ". " + menuItems.get(i - 1).getMenuItem());
        }
    }
}
