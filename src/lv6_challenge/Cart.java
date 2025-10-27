package lv6_challenge;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    // 속성
    private List<MenuItem> cartItems;

    // 생성자

    // 기능
    // getter
    List<MenuItem> getCartItems() {
        return this.cartItems;
    }

    // MenuItem 객체를 cartItems 리스트에 저장
    void saveItem(MenuItem menuItem) {
        this.cartItems.add(menuItem);
    }

    // 리스트에 있는 CartItem을 순차적으로 프린트
    void printCartItems() {
        for (int i = 1; i <= cartItems.size(); i++) {
            System.out.println(i + ". " + cartItems.get(i - 1));
        }
    }
}