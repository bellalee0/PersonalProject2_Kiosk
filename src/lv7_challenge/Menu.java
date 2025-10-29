package lv7_challenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Menu {
    // 속성
    private List<MenuItem> menuItems;
    private String categoryName;

    // 생성자(오버라이드)
    Menu(String categoryName) {
        this.categoryName = categoryName;
        this.menuItems = new ArrayList<>();
    }

    // toString 오버라이드
    @Override
    public String toString() { return categoryName.toUpperCase(); }

    List<MenuItem> getMenu() {
        return menuItems.isEmpty() ? Collections.emptyList() : this.menuItems;
    }

    // MenuItem 객체를 리스트에 저작
    void saveMenus(List<MenuItem> menus) { this.menuItems = new ArrayList<>(menus); }

    // 리스트에 있는 MenuItem을 순차적으로 프린트
    void printMenus() {
        IntStream.rangeClosed(1, menuItems.size())
                .forEach(i -> System.out.println(i + ". " + menuItems.get(i-1)));
    }
}
