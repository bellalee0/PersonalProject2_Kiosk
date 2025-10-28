package lv6_challenge;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    // 속성
    private List<MenuItem> cartItems = new ArrayList<>();

    // 생성자

    // 기능
    // getter
    List<MenuItem> getCartItems() {
        return cartItems.isEmpty() ? Collections.emptyList() : this.cartItems;
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

    // Empty 여부
    boolean isEmpty() {
        return this.cartItems.isEmpty();
    }

    // 장바구니 비우기
    void clearCart() {
        this.cartItems.clear();
    }

    // 원하는 항목만 삭제하기
    void removeItem(int index) {
        this.cartItems.remove(index);
    }

    // 장바구니 내 메뉴의 총액 출력
    BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal("0");
        for (int i = 0; i < this.cartItems.size(); i++) {
            totalPrice = totalPrice.add(this.cartItems.get(i).getPrice());
        }
        return totalPrice;
    }
}